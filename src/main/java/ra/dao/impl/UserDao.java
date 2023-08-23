package ra.dao.impl;

import org.springframework.stereotype.Component;
import ra.dao.IGenericDao;
import ra.dto.request.FormLoginDto;
import ra.model.User;
import ra.ultil.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao implements IGenericDao<User,Integer> {

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn =null;
        try{
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call getAllUsers}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar_url"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getInt("role_id"));
                user.setStatus(rs.getBoolean("status"));
                list.add(user);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(User user) {

        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = null;
            if(user.getId()==0){
                // chức năng thêm mới
                callSt=conn.prepareCall("{call insertUser(?,?,?)}");
                callSt.setString(1,user.getUsername());
                callSt.setString(2,user.getEmail());
                callSt.setString(3,user.getPassword());
                // thực thi câu lệnh sql
                callSt.executeUpdate();
            }else {
                // cập nhật
                callSt = conn.prepareCall("{call updateUser(?,?,?,?)}");
                callSt.setInt(1, user.getId());
                callSt.setString(2,user.getAvatar());
                callSt.setString(3,user.getPhone());
                callSt.setString(4,user.getAddress());

                // thực thi câu lệnh sql
                callSt.executeUpdate();
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public User findById(Integer id) {
        User user =null;
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = conn.prepareCall("{call findUserById(?)}");
            // truyền đối số
            // thực thi câu lệnh xóa
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getBoolean("status"));
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }
    @Override
    public void delete(Integer id){
    }
    public User login (FormLoginDto formLoginDto){
        User user =null;
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();

            CallableStatement callSt = conn.prepareCall("{call login(?,?)}");
            callSt.setString(1,formLoginDto.getUsername());
            callSt.setString(2,formLoginDto.getPassword());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar_url"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getInt("role_id"));
                user.setStatus(rs.getBoolean("status"));
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }

    public void tongleUserStatus(Integer id){
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call tongleUserStatus(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}