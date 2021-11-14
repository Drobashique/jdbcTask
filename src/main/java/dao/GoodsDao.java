package dao;

import entity.GoodsEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao implements Idao<GoodsEntity>{
    final Connection connection;

    public GoodsDao(Connection connection){
        this.connection=connection;
    }

    @Override
    public GoodsEntity get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from goods where inner_code = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String goodsName = rs.getString(1);
                int inId = rs.getInt(2);
                return new GoodsEntity(goodsName, inId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException(" goods with inner_code = " + id + " not found");
    }

    @Override
    public List<GoodsEntity> getAll() {
        final List<GoodsEntity> list = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("select * from goods");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int inId = rs.getInt(2);
                list.add(new GoodsEntity(name, inId));
            }
        }   catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return list;
    }

    @Override
    public void save(GoodsEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into goods (name, inner_code) values (?,?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getInnerCode());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(GoodsEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update goods set name = ? where inner_code = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getInnerCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(GoodsEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from goods where inner_code = ?")) {
            preparedStatement.setInt(1, entity.getInnerCode());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException(" goods called " + entity.getName() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
