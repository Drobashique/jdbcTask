package dao;

import entity.OrganizationEntity;
import entity.WaybillEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaybillDao implements Idao<WaybillEntity>{
    final Connection connection;

    public WaybillDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public WaybillEntity get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from waybill where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idWb = rs.getInt(1);
                Date date = rs.getDate(2);
                int inn = rs.getInt(3);
                return new WaybillEntity(idWb,date,inn);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException("not found");
    }

    @Override
    public List<WaybillEntity> getAll() {
        final List<WaybillEntity> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from waybill");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int idWb = rs.getInt(1);
                Date date = rs.getDate(2);
                int inn = rs.getInt(3);
                list.add(new WaybillEntity(idWb,date,inn));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(WaybillEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into waybill (id, waybill_date, waybill_organization) values (?,?,?)")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setDate(2, entity.getOrgDate());
            preparedStatement.setInt(3, entity.getOrgInn());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(WaybillEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update waybill set waybill_date = ?,waybill_organization = ? where id = ?")) {
            preparedStatement.setDate(1, entity.getOrgDate());
            preparedStatement.setInt(2, entity.getOrgInn());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(WaybillEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from waybill where id = ?")) {
            preparedStatement.setInt(1, entity.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException( "not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
