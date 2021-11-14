package dao;

import entity.GoodsEntity;
import entity.OrganizationEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDao implements Idao<OrganizationEntity>{

    final Connection connection;

    public OrganizationDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public OrganizationEntity get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from organization where inn = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String orgName = rs.getString(1);
                int inn = rs.getInt(2);
                Long checkingAccount = rs.getLong(3);
                return new OrganizationEntity(orgName, inn,checkingAccount);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException("not found");
    }

    @Override
    public List<OrganizationEntity> getAll() {
        final List<OrganizationEntity> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from organization");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String orgName = rs.getString(1);
                int inn = rs.getInt(2);
                Long checkingAccount = rs.getLong(3);
                list.add(new OrganizationEntity(orgName, inn,checkingAccount));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(OrganizationEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into organization (name, inn, checking_account) values (?,?,?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getInn());
            preparedStatement.setLong(3, entity.getCheckingAccount());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(OrganizationEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update organization set inn = ?,checking_account = ? where name = ?")) {
            preparedStatement.setInt(1, entity.getInn());
            preparedStatement.setLong(2, entity.getCheckingAccount());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(OrganizationEntity entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from organization where name = ?")) {
            preparedStatement.setString(1, entity.getName());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException( "not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
