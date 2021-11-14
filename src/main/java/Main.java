import dao.*;
import entity.GoodsEntity;
import entity.OrganizationEntity;
import entity.WaybillEntity;
import entity.WaybillPositionEntity;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Main {

    public static void main(String[] args) throws SQLException {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/organization", "postgres", "postgres")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/organization", "postgres", "postgres")) {
            System.out.println("Connection complteted");
            final OrganizationDao organizationDAO = new OrganizationDao(connection);
            final GoodsDao goodsDao = new GoodsDao(connection);
            final WaybillDao waybillDao = new WaybillDao(connection);
            final WaybillPositionDao waybillPositionDao = new WaybillPositionDao(connection);
            organizationDAO.save(new OrganizationEntity("Company1", 1, 12L));
            organizationDAO.save(new OrganizationEntity("Company2", 2, 15L));
            organizationDAO.save(new OrganizationEntity("Company3", 3, 12L));
            organizationDAO.save(new OrganizationEntity("Company4", 4, 15L));
            organizationDAO.save(new OrganizationEntity("Company5", 5, 12L));
            organizationDAO.save(new OrganizationEntity("Company6", 6, 15L));
            organizationDAO.save(new OrganizationEntity("Company7", 7, 12L));
            organizationDAO.save(new OrganizationEntity("Company8", 8, 15L));
            organizationDAO.save(new OrganizationEntity("Company9", 9, 12L));
            organizationDAO.save(new OrganizationEntity("Company0", 10, 15L));
            organizationDAO.save(new OrganizationEntity("Company11", 11, 12L));
            organizationDAO.save(new OrganizationEntity("Company12", 12, 15L));
            organizationDAO.save(new OrganizationEntity("Company13", 13, 12L));
            organizationDAO.save(new OrganizationEntity("Company14", 14, 15L));

            goodsDao.save(new GoodsEntity("Good1", 1));
            goodsDao.save(new GoodsEntity("Good2", 2));

            waybillDao.save(new WaybillEntity(1, new Date(12341273512381253L), 1));
            waybillDao.save(new WaybillEntity(2, new Date(12341273253L), 2));
            waybillDao.save(new WaybillEntity(3, new Date(12341273512381253L), 3));
            waybillDao.save(new WaybillEntity(4, new Date(12341273253L), 4));
            waybillDao.save(new WaybillEntity(5, new Date(12341273512381253L), 5));
            waybillDao.save(new WaybillEntity(6, new Date(12341273253L), 6));
            waybillDao.save(new WaybillEntity(7, new Date(12341273512381253L), 7));
            waybillDao.save(new WaybillEntity(8, new Date(12341273253L), 8));
            waybillDao.save(new WaybillEntity(9, new Date(12341273512381253L), 9));
            waybillDao.save(new WaybillEntity(10, new Date(12341273253L), 10));
            waybillDao.save(new WaybillEntity(11, new Date(12341273512381253L), 11));
            waybillDao.save(new WaybillEntity(12, new Date(12341273253L), 12));
            waybillDao.save(new WaybillEntity(13, new Date(12341273512381253L), 13));
            waybillDao.save(new WaybillEntity(14, new Date(12341273253L), 14));


            waybillPositionDao.save(new WaybillPositionEntity(1,1, (float) 45.3,15,1));
            waybillPositionDao.save(new WaybillPositionEntity(2,2, (float) 4,17,2));
            waybillPositionDao.save(new WaybillPositionEntity(3,1, (float) 45.3,18,3));
            waybillPositionDao.save(new WaybillPositionEntity(4,2, (float) 4,19,4));
            waybillPositionDao.save(new WaybillPositionEntity(5,1, (float) 45.3,111,5));
            waybillPositionDao.save(new WaybillPositionEntity(6,2, (float) 4,222,6));
            waybillPositionDao.save(new WaybillPositionEntity(7,1, (float) 45.3,343,7));
            waybillPositionDao.save(new WaybillPositionEntity(8,2, (float) 4,35,8));
            waybillPositionDao.save(new WaybillPositionEntity(9,1, (float) 45.3,345,9));
            waybillPositionDao.save(new WaybillPositionEntity(10,2, (float) 4,643,10));
            waybillPositionDao.save(new WaybillPositionEntity(11,1, (float) 45.3,123,11));
            waybillPositionDao.save(new WaybillPositionEntity(12,2, (float) 4,2221,12));
            waybillPositionDao.save(new WaybillPositionEntity(13,1, (float) 45.3,56,13));
            waybillPositionDao.save(new WaybillPositionEntity(14,2, (float) 4,78,14));

            System.out.println(goodsDao.getAll());
            System.out.println(goodsDao.get(1).getName());
            goodsDao.update(new GoodsEntity("CMP", 2));
            System.out.println(goodsDao.get(2).getName());
            final Reports reports = new Reports(connection);
            reports.topTenReport();
            reports.moreThanReport(5);
            reports.everyDayPeriodCostAmountReport(new Date(1234127325L), new Date(12341273259L));
            reports.goodsPeriodListReport(new Date(1234127325L), new Date(1234127325253L));
            goodsDao.delete(new GoodsEntity("Good2", 2));
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }



}