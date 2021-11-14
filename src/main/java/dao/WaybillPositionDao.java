package dao;

import entity.WaybillEntity;
import entity.WaybillPositionEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaybillPositionDao implements Idao<WaybillPositionEntity>{
    final Connection connection;

    public WaybillPositionDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public WaybillPositionEntity get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from waybill_position where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idWP = rs.getInt(1);
                int goodsId = rs.getInt(2);
                Float price = rs.getFloat(3);
                int amount = rs.getInt(4);
                int waybillId = rs.getInt(5);
                return new WaybillPositionEntity(idWP,goodsId,price,amount,waybillId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException("not found");
    }

    @Override
    public List<WaybillPositionEntity> getAll() {
        final List<WaybillPositionEntity> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from waybill_position");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idWP = rs.getInt(1);
                int goodsId = rs.getInt(2);
                Float price = rs.getFloat(3);
                int amount = rs.getInt(4);
                int waybillId = rs.getInt(5);
                list.add(new WaybillPositionEntity(idWP,goodsId,price,amount,waybillId));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(WaybillPositionEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into waybill_position (id, goods_name, price, amount,waybill_id) values (?,?,?,?,?)")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getGoodsID());
            preparedStatement.setFloat(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getAmount());
            preparedStatement.setInt(5, entity.getWaybillId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(WaybillPositionEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update waybill_position set goods_name = ?,price = ?, amount = ? where id = ?")) {
            preparedStatement.setInt(1, entity.getGoodsID());
            preparedStatement.setFloat(2, entity.getPrice());
            preparedStatement.setInt(3, entity.getAmount());
            preparedStatement.setInt(4, entity.getWaybillId());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(WaybillPositionEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from waybill_position where id = ?")) {
            preparedStatement.setInt(1, entity.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException( "not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
