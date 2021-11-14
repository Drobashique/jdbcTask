package dao;

import java.sql.*;
import java.text.DecimalFormat;

public final class Reports {
    final Connection connection;

    public Reports(Connection connection) {
        this.connection = connection;
    }
    public void topTenReport(){
        try(Statement statement = connection.createStatement()){
            try(ResultSet rs = statement.executeQuery(
                    "SELECT organization.name, sum(waybill_position.amount) as sum10 from waybill_position JOIN waybill on waybill_position.waybill_id = waybill.id JOIN organization on waybill.waybill_organization = organization.inn group by waybill_id, organization.name order by sum10 desc limit 10;")) {
                while (rs.next()){
                    System.out.println("name: "+rs.getString(1)+" - amoint: "+rs.getInt(2));
                }
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            exception.getMessage();
        }

    }

    public void moreThanReport(int amountC){
        try(Statement statement = connection.createStatement()){
            try(ResultSet rs = statement.executeQuery(
                    "SELECT organization.name, sum(price) as sumM from waybill_position JOIN waybill on waybill_position.waybill_id = waybill.id JOIN organization on waybill.waybill_organization = organization.inn group by waybill_id, organization.name\n" +
                            " having sum(price) > " +amountC+ " order by sumM desc;")
            ) {
                while (rs.next()){
                    System.out.println("name: "+rs.getString(1)+" - sum: "+rs.getString(2));
                }
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

    }

    public void everyDayPeriodCostAmountReport(Date dateStart, Date dateFinish){
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT waybill.waybill_date, sum(price) as sumCA, sum(amount) as sumAm from waybill_position JOIN waybill on waybill_position.waybill_id = waybill.id \n" +
                        "group by waybill_date having waybill.waybill_date between ? and ? order by waybill_date desc;")
        ){
            statement.setDate(1,dateStart);
            statement.setDate(2,dateFinish);
            try(ResultSet rs = statement.executeQuery()) {
                double summ = 0,count = 0;
                while (rs.next()){
                    String summa = rs.getString(2)
                            .replace("$","")
                            .replace(",","");
                    summ+= Double.parseDouble(summa);
                    count+= rs.getInt(3);
                    System.out.println("date: "+rs.getDate(1)+" - summ: "+rs.getString(2)+" - count: "+rs.getInt(3));
                }
                System.out.println("Total: "+summ+" Count: "+count);
            }

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

    }

    public void averageCostPeriodReport(Date dateStart, Date dateFinish){

    }

    public void goodsPeriodListReport(Date dateStart, Date dateFinish){
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT waybill_position.waybill_id, waybill_position.price, waybill_position.amount FROM waybill_position JOIN waybill on waybill_position.waybill_id = waybill.id where waybill.waybill_date between ? and ?")
        ){
            statement.setDate(1,dateStart);
            statement.setDate(2,dateFinish);
            try(ResultSet rs = statement.executeQuery()) {
                double summ = 0,count = 0;
                while (rs.next()){
                    System.out.println("waybill_id: "+rs.getInt(1)+" - price: "+rs.getString(2)+" - quantity: "+rs.getInt(3));
                    String summa = rs.getString(2);
                    count+= rs.getInt(3);
                    summ+= Double.parseDouble(summa);
                }
                String formattedRes = new DecimalFormat("#0.00").format(summ/count);
                System.out.println("Av price: "+formattedRes);
            }

        }catch (SQLException exception){
            exception.getMessage();
        }
    }

}
