package org.nearbyshops.billingdao;

import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.globals.Globals;
import org.nearbyshops.model.Shop;
import org.nearbyshops.ModelBilling.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DAOAddBalance {


    private HikariDataSource dataSource = Globals.getDataSource();



    public int addBalanceToShop(int shopAdminID,double amountToAdd)
    {



        Connection connection = null;
        PreparedStatement statementUpdate = null;
        PreparedStatement statementTransaction = null;


        int rowCountItems = -1;

        String update = "";
        String createTransaction = "";





        update =  " UPDATE " + Shop.TABLE_NAME
                + " SET "    + Shop.ACCOUNT_BALANCE + " = " + Shop.ACCOUNT_BALANCE + " + ? "
                + " WHERE "  + Shop.SHOP_ADMIN_ID  + " = ?";

        createTransaction = "INSERT INTO " + Transaction.TABLE_NAME
                + "("

                + Transaction.USER_ID + ","

                + Transaction.TITLE + ","
                + Transaction.DESCRIPTION + ","

                + Transaction.TRANSACTION_TYPE + ","
                + Transaction.TRANSACTION_AMOUNT + ","

                + Transaction.IS_CREDIT + ","

                + Transaction.BALANCE_AFTER_TRANSACTION + ""

                + ") "
                + " SELECT "

                + Shop.TABLE_NAME + "." + Shop.SHOP_ADMIN_ID + ","
                + " 'Credit added',"
                + " 'Credit added by staff',"

//                + " ? ,"
//                + " ? ,"

                + Transaction.TRANSACTION_TYPE_PAYMENTS + ","
                + "? ,"

                + " true " + ","
                + Shop.TABLE_NAME + "." + Shop.ACCOUNT_BALANCE + ""

                + " FROM " + Shop.TABLE_NAME
                + " WHERE " + Shop.TABLE_NAME + "." + Shop.SHOP_ADMIN_ID + " = ? ";












        try {

            connection = dataSource.getConnection();
            connection.setAutoCommit(false);



            statementUpdate = connection.prepareStatement(update,PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;

            statementUpdate.setObject(++i,amountToAdd);
            statementUpdate.setObject(++i,shopAdminID);


            rowCountItems = statementUpdate.executeUpdate();


            if(rowCountItems==1)
            {
                statementTransaction = connection.prepareStatement(createTransaction,PreparedStatement.RETURN_GENERATED_KEYS);
                i = 0;
                statementTransaction.setObject(++i,amountToAdd);
                statementTransaction.setObject(++i,shopAdminID);


                rowCountItems = rowCountItems + statementTransaction.executeUpdate();
            }


            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            if (connection != null) {
                try {
                    rowCountItems = 0;

                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        finally
        {

            if (statementUpdate != null) {
                try {
                    statementUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statementTransaction != null) {
                try {
                    statementTransaction.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            try {

                if(connection!=null)
                {connection.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        return rowCountItems;
    }



}
