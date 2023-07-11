package com.banma.orm;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.banma.annotation.ORMAnnoHelper;
import com.google.protobuf.Descriptors.FileDescriptor;

public class DBSessionFacory {
	private DBSource dbSource;// 数据源
	private Properties props;// 数据源连接的属性

	public DBSessionFacory() {
		props = new Properties();
		try {
			// 从属性资源文件中加载key-value
			props.load(ClassLoader.getSystemResourceAsStream("dbConfig.properties"));
			dbSource = new DBSource(props);
			Connection conn = dbSource.openConnection();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 打开数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public DBSession openSession() throws Exception {
		return new DBSession(dbSource.openConnection());
	}

	/**
	 * 操作数据库 增删改查
	 * 
	 * @author WHASSUPMAN
	 *
	 */
	public static class DBSession {
		private Connection conn;// 数据库连接对象

		public DBSession(Connection conn) {
			this.conn = conn;
		}

		/**
		 * 查询所有数据
		 * 
		 * @param <T>
		 * @param cls
		 * @return
		 * @throws Exception
		 */
		public <T> List<T> list(Class<T> cls) throws Exception {
			// select x,y,z from tb_name
			String sql = "select %s from %s";

			// 生成查询字段的列表
			// 通过反射取到类里面的所有字段
			StringBuilder columns = new StringBuilder();
			Field[] fs = cls.getDeclaredFields();// 实体类的所有属性字段
			for (int i = 0, len = fs.length; i < len; i++) {
				columns.append(ORMAnnoHelper.getColumnName(fs[i]));
				if (i != len - 1) {
					columns.append(",");
				}
			}

			// 生成完整的查询sql语句
			sql = String.format(sql, columns.toString(), ORMAnnoHelper.getTableName(cls));
			System.out.println("Statement SQL:\n" + sql);

			// 创建执行sql的语句对象(statement PreparedStatement)
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 1.实例化实体类对象
			// 2.读取结果集中的字段数据
			// 3.注入实体对象的属性上

			List<T> list = new ArrayList<>();
			T obj = null;
			while (rs.next()) {
				// 1.实例化实体类对象
				obj = cls.newInstance();
				// 2.读取指定字段的数据并注入对应对象的属性上
				// 判断字段的类型后 从查询结果集中获取不同类型的数据
				for (Field f : fs) {
					f.setAccessible(true);
					Class<?> type = f.getType();
					if (type == String.class) {
						// 3.注入实体对象的属性上
						f.set(obj, rs.getString(ORMAnnoHelper.getColumnName(f)));
					} else if (type == int.class || type == Integer.class) {
						f.set(obj, rs.getString(ORMAnnoHelper.getColumnName(f)));
					} else if (type == double.class || type == Double.class) {
						f.set(obj, rs.getDouble(ORMAnnoHelper.getColumnName(f)));
					} else if (type == Date.class) {
						f.set(obj, rs.getDate(ORMAnnoHelper.getColumnName(f)));
					} else if (type == int.class || type == Integer.class) {
						f.set(obj, rs.getString(ORMAnnoHelper.getColumnName(f)));
					}
				}

				// 将实体对象添加到list集合中
				list.add(obj);
			}
			stmt.close();
			return list;
		}

		/**
		 * 插入数据
		 * 
		 * @param obj
		 * @return
		 * @throws Exception
		 */
		public int save(Object obj) throws Exception {
			// 生成插入的SQL：insert 表名(x,xx,xxx)values(?,?,?)
			String sql = "insert into %s(%s) values(%s)";
			StringBuilder columns = new StringBuilder();
			StringBuilder params = new StringBuilder();

			// 获取实体对象中的所有字段
			Field[] fs = obj.getClass().getDeclaredFields();
			for (int i = 0, len = fs.length; i < len; i++) {
				columns.append(ORMAnnoHelper.getColumnName(fs[i]));
				params.append("?");
				if (i != len - 1) {
					columns.append(",");
					params.append(",");
				}
			}

			// 生成最终的SQL
			sql = String.format(sql, ORMAnnoHelper.getTableName(obj.getClass()), columns.toString(), params.toString());
			System.out.println("Insert SQL:\n" + sql);

			// 创建预处理SQL语句的对象
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 设置预处理SQL语句的每个参数值
			// 参数可以用下面的封装 还没实现
			int i = 1;// 预处理SQL参数索引从1开始
			for (Field f : fs) {
				f.setAccessible(true);
				Class<?> type = f.getType();
				if (type == String.class) {
					pstmt.setString(i, String.valueOf(f.get(obj)));
				} else if (type == int.class || type == Integer.class) {
					pstmt.setInt(i, f.getInt(obj));
				} else if (type == double.class || type == Double.class) {
					pstmt.setDouble(i, f.getDouble(obj));
				} else if (type == float.class || type == Float.class) {
					pstmt.setFloat(i, f.getFloat(obj));
				}else if (type == Date.class) {
					Date date = (Date)f.get(obj);
					pstmt.setDate(i, new java.sql.Date(date.getTime()));
				} 
				i++;
			}
			// 执行预处理语句
			int rows = pstmt.executeUpdate();
			pstmt.close();
			return rows;
		}

		/**
		 * 更新
		 * 
		 * @param obj
		 * @return
		 * 
		 */
		public int update(Object obj) throws Exception {
			// SQL：update 表名 set xx=?,xxx=? where {id}=?
			String sql = "update %s set %s where %s";
			StringBuilder updateColumns = new StringBuilder();
			String where = "";

			// 拿到所有字段(包含主键的字段)
			Field[] fs = obj.getClass().getDeclaredFields();
			// 需要更新的字段放入集合中
			List<Field> updateFields = new ArrayList<>();
			Field f = null;
			for (int i = 0, len = fs.length; i < len; i++) {
				f = fs[i];
				// 判断字段是否为主键
				if (ORMAnnoHelper.isId(f)) {
					f.setAccessible(true);
					where = ORMAnnoHelper.getColumnName(f) + "=";
					// 判断主键字段的类型 如果是字符串 前后加单引号
					if (f.getType() == String.class) {
						where += "'" + String.valueOf(f.get(obj)) + "'";
					} else {
						where += f.get(obj);
					}
					continue;
				}
				// 走到这里 字段都是非主键
				updateColumns.append(ORMAnnoHelper.getColumnName(f) + "=?");
				if (i != len - 1) {
					updateColumns.append(",");
				}
				
				//将参与更新的字段添加到集合中
				updateFields.add(f);
				f=null;
				
			}
			// 生成最终的SQL
			sql = String.format(sql, ORMAnnoHelper.getTableName(obj.getClass()),
					updateColumns.toString(), where.toString());
			
			System.out.println("Update SQL:\n" + sql);
			
			//执行更新语句 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//参数的处理，封装到方法中
			paramasHandler(obj, updateFields, pstmt);
			int row = pstmt.executeUpdate();
			pstmt.close();
			return row;
		}

		/**
		 * 根据主键删除数据
		 * 
		 * @param cls
		 * @param id
		 * @return
		 */
		public int delete(Class cls, Object id) {
			return 0;
		}

		public void close() {// 关闭数据库连接
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
		
		private void paramasHandler(Object obj,List<Field> fields, PreparedStatement pstmt) throws Exception{
			Class<?> type=null;
			Field f = null;
			for (int i=0,len=fields.size();i<len;i++) {
				f=fields.get(i);
				f.setAccessible(true);
				//得到字段的类型
				type=f.getType();
				
				if(type==String.class) {
					pstmt.setString(i+1, String.valueOf(f.get(obj)));
				}else if (type == int.class || type == Integer.class) {
					pstmt.setInt(i+1, f.getInt(obj));
				} else if (type == double.class || type == Double.class) {
					pstmt.setDouble(i+1, f.getDouble(obj));
				} else if (type == float.class || type == Float.class) {
					pstmt.setFloat(i+1, f.getFloat(obj));
				} else if (type == long.class || type == Long.class) {
					pstmt.setLong(i+1, f.getLong(obj));
				} else if (type == Date.class) {
					Date date = (Date)f.get(obj);
					pstmt.setDate(i+1, new java.sql.Date(date.getTime()));
				} 
				
			}
		}
		
	}

}
