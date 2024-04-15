package org.nearbyshops.DAOPreparedSettings;


import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.Globals.GlobalConstants;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.ModelSettings.ServiceConfigurationLocal;

import java.sql.*;
import java.util.ArrayList;


public class ServiceConfigurationDAOPrepared {


	private HikariDataSource dataSource = Globals.getDataSource();


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();	
	}


	public int saveService(ServiceConfigurationLocal serviceConfigurationLocal)
	{

		Connection connection = null;
		PreparedStatement statement = null;
		int rowIdOfInsertedRow = -1;

		String insertItemCategory = "INSERT INTO "
				+ ServiceConfigurationLocal.TABLE_NAME
				+ "("

//				+ ServiceConfigurationLocal.IMAGE_PATH + ","
				+ ServiceConfigurationLocal.LOGO_IMAGE_PATH + ","
				+ ServiceConfigurationLocal.BACKDROP_IMAGE_PATH + ","

				+ ServiceConfigurationLocal.SERVICE_NAME + ","
				+ ServiceConfigurationLocal.HELPLINE_NUMBER + ","

				+ ServiceConfigurationLocal.DESCRIPTION_SHORT + ","
				+ ServiceConfigurationLocal.DESCRIPTION_LONG + ","

				+ ServiceConfigurationLocal.ADDRESS + ","
				+ ServiceConfigurationLocal.CITY + ","
				+ ServiceConfigurationLocal.PINCODE + ","
				+ ServiceConfigurationLocal.LANDMARK + ","

				+ ServiceConfigurationLocal.STATE + ","
				+ ServiceConfigurationLocal.COUNTRY + ","
				+ ServiceConfigurationLocal.ISO_COUNTRY_CODE + ","
				+ ServiceConfigurationLocal.ISO_LANGUAGE_CODE + ","
				+ ServiceConfigurationLocal.ISO_CURRENCY_CODE + ","

//				+ ServiceConfigurationLocal.SERVICE_TYPE + ","
//				+ ServiceConfigurationLocal.SERVICE_LEVEL + ","

				+ ServiceConfigurationLocal.LAT_CENTER + ","
				+ ServiceConfigurationLocal.LON_CENTER + ","
				+ ServiceConfigurationLocal.SERVICE_RANGE + ","

				+ ServiceConfigurationLocal.UPDATED + ","
				+ ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID + ""
				+ " ) VALUES (?,? ,?,?,  ?,? ,?,?,?,? ,?,?,?,?,? ,?,?,? ,?,?)";
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(insertItemCategory,Statement.RETURN_GENERATED_KEYS);

			int i = 0;

//			statement.setString(1,serviceConfigurationLocal.getImagePath());
			statement.setString(++i, serviceConfigurationLocal.getLogoImagePath());
			statement.setString(++i, serviceConfigurationLocal.getBackdropImagePath());

			statement.setString(++i, serviceConfigurationLocal.getServiceName());
			statement.setString(++i, serviceConfigurationLocal.getHelplineNumber());

			statement.setString(++i, serviceConfigurationLocal.getDescriptionShort());
			statement.setString(++i, serviceConfigurationLocal.getDescriptionLong());

			statement.setString(++i, serviceConfigurationLocal.getAddress());
			statement.setString(++i, serviceConfigurationLocal.getCity());
			statement.setObject(++i, serviceConfigurationLocal.getPincode());
			statement.setString(++i, serviceConfigurationLocal.getLandmark());

			statement.setString(++i, serviceConfigurationLocal.getState());
			statement.setString(++i, serviceConfigurationLocal.getCountry());
			statement.setString(++i, serviceConfigurationLocal.getISOCountryCode());
			statement.setString(++i, serviceConfigurationLocal.getISOLanguageCode());
			statement.setString(++i, serviceConfigurationLocal.getISOCurrencyCode());

//			statement.setObject(++i, serviceConfigurationLocal.getServiceType());
//			statement.setObject(++i, serviceConfigurationLocal.getServiceLevel());

			statement.setObject(++i, serviceConfigurationLocal.getLatCenter());
			statement.setObject(++i, serviceConfigurationLocal.getLonCenter());
			statement.setObject(++i, serviceConfigurationLocal.getServiceRange());

			statement.setTimestamp(++i,new Timestamp(System.currentTimeMillis()));
			statement.setObject(++i,1);




			rowIdOfInsertedRow = statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if(rs.next())
			{
				rowIdOfInsertedRow = rs.getInt(1);
			}
			
			
			
			System.out.println("Key autogenerated Save CurrentServiceConfiguration: " + rowIdOfInsertedRow);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return rowIdOfInsertedRow;
	}




	public int updateService(ServiceConfigurationLocal serviceConfigurationLocal)
	{

		// Ensure the service configuration row exist before being updated
		if(getServiceConfiguration(null,null)==null){
			saveService(getDefaultConfiguration());
		}


		String updateStatement = "UPDATE " + ServiceConfigurationLocal.TABLE_NAME

				+ " SET "

//				+ ServiceConfigurationLocal.IMAGE_PATH + " = ?,"
				+ ServiceConfigurationLocal.LOGO_IMAGE_PATH + " = ?,"
				+ ServiceConfigurationLocal.BACKDROP_IMAGE_PATH + " = ?,"

				+ ServiceConfigurationLocal.SERVICE_NAME + " = ?,"
				+ ServiceConfigurationLocal.HELPLINE_NUMBER + " = ?,"

				+ ServiceConfigurationLocal.DESCRIPTION_SHORT + "=?,"
				+ ServiceConfigurationLocal.DESCRIPTION_LONG + "=?,"

				+ ServiceConfigurationLocal.ADDRESS + " = ?,"

				+ ServiceConfigurationLocal.CITY + " = ?,"
				+ ServiceConfigurationLocal.PINCODE + " = ?,"
				+ ServiceConfigurationLocal.LANDMARK + " = ?,"

				+ ServiceConfigurationLocal.STATE + " = ?,"
				+ ServiceConfigurationLocal.COUNTRY + " = ?,"
				+ ServiceConfigurationLocal.ISO_COUNTRY_CODE + " = ?,"

				+ ServiceConfigurationLocal.ISO_LANGUAGE_CODE + " = ?,"
				+ ServiceConfigurationLocal.ISO_CURRENCY_CODE + " = ?,"

//				+ ServiceConfigurationLocal.SERVICE_TYPE + " = ?,"
//				+ ServiceConfigurationLocal.SERVICE_LEVEL + " = ?,"

				+ ServiceConfigurationLocal.LAT_CENTER + " = ?,"
				+ ServiceConfigurationLocal.LON_CENTER + " = ?,"
				+ ServiceConfigurationLocal.SERVICE_RANGE + " = ?,"

				+ ServiceConfigurationLocal.UPDATED + " = ?"
				+ " WHERE "
				+ ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID + " = ?";


		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);

			int i = 0;
//			statement.setString(1,serviceConfigurationLocal.getImagePath());
			statement.setString(++i, serviceConfigurationLocal.getLogoImagePath());
			statement.setString(++i, serviceConfigurationLocal.getBackdropImagePath());

			statement.setString(++i, serviceConfigurationLocal.getServiceName());
			statement.setString(++i, serviceConfigurationLocal.getHelplineNumber());


			statement.setString(++i, serviceConfigurationLocal.getDescriptionShort());
			statement.setString(++i, serviceConfigurationLocal.getDescriptionLong());

			statement.setString(++i, serviceConfigurationLocal.getAddress());

			statement.setString(++i, serviceConfigurationLocal.getCity());
			statement.setObject(++i, serviceConfigurationLocal.getPincode());
			statement.setString(++i, serviceConfigurationLocal.getLandmark());

			statement.setString(++i, serviceConfigurationLocal.getState());
			statement.setString(++i, serviceConfigurationLocal.getCountry());
			statement.setString(++i, serviceConfigurationLocal.getISOCountryCode());

			statement.setString(++i, serviceConfigurationLocal.getISOLanguageCode());
			statement.setString(++i, serviceConfigurationLocal.getISOCurrencyCode());

//			statement.setObject(++i, serviceConfigurationLocal.getServiceType());
//			statement.setObject(++i, serviceConfigurationLocal.getServiceLevel());

			statement.setObject(++i, serviceConfigurationLocal.getLatCenter());
			statement.setObject(++i, serviceConfigurationLocal.getLonCenter());
			statement.setObject(++i, serviceConfigurationLocal.getServiceRange());

			statement.setTimestamp(++i,new Timestamp(System.currentTimeMillis()));

			statement.setObject(++i,1);


			updatedRows = statement.executeUpdate();
			
			
			System.out.println("Total rows updated: " + updatedRows);	
			
			//conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;
		
	}




	public int deleteService(int serviceID)
	{
		
		String deleteStatement = "DELETE FROM " + ServiceConfigurationLocal.TABLE_NAME
				+ " WHERE " + ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID + " = ?";
		
		
		Connection connection= null;
		PreparedStatement statement = null;
		int rowsCountDeleted = 0;
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(deleteStatement);

			statement.setInt(1,serviceID);
			rowsCountDeleted = statement.executeUpdate();
			System.out.println(" Deleted Count: " + rowsCountDeleted);

			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return rowsCountDeleted;
	}
	


	
	
	
	public ArrayList<ServiceConfigurationLocal> readServices(Integer serviceLevel, Integer serviceType,
                                                             Double latCenterQuery, Double lonCenterQuery,
                                                             String sortBy,
                                                             int limit, int offset)
	{


		String queryNormal = "SELECT " + " 6371 * acos(cos( radians("
							+ latCenterQuery + ")) * cos( radians( " + ServiceConfigurationLocal.LAT_CENTER
							+ ")) * cos(radians( "
							+ ServiceConfigurationLocal.LON_CENTER + ") - radians(" + lonCenterQuery + "))"
							+ " + sin( radians(" + latCenterQuery+ ")) * sin(radians(" + ServiceConfigurationLocal.LAT_CENTER + "))) as distance" + ","

							+ ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID + ","
							+ ServiceConfigurationLocal.LOGO_IMAGE_PATH + ","
							+ ServiceConfigurationLocal.BACKDROP_IMAGE_PATH + ","

							+ ServiceConfigurationLocal.SERVICE_NAME + ","
							+ ServiceConfigurationLocal.HELPLINE_NUMBER + ","

							+ ServiceConfigurationLocal.DESCRIPTION_SHORT + ","
							+ ServiceConfigurationLocal.DESCRIPTION_LONG + ","

							+ ServiceConfigurationLocal.ADDRESS + ","
							+ ServiceConfigurationLocal.CITY + ","
							+ ServiceConfigurationLocal.PINCODE + ","
							+ ServiceConfigurationLocal.LANDMARK + ","

							+ ServiceConfigurationLocal.STATE + ","
							+ ServiceConfigurationLocal.COUNTRY + ","
							+ ServiceConfigurationLocal.ISO_COUNTRY_CODE + ","

							+ ServiceConfigurationLocal.ISO_LANGUAGE_CODE + ","
							+ ServiceConfigurationLocal.ISO_CURRENCY_CODE + ","

//							+ ServiceConfigurationLocal.SERVICE_TYPE + ","
//							+ ServiceConfigurationLocal.SERVICE_LEVEL + ","

							+ ServiceConfigurationLocal.LAT_CENTER + ","
							+ ServiceConfigurationLocal.LON_CENTER + ","
							+ ServiceConfigurationLocal.SERVICE_RANGE + ","

							+ ServiceConfigurationLocal.CREATED + ","
							+ ServiceConfigurationLocal.UPDATED + ""
							+ " FROM " + ServiceConfigurationLocal.TABLE_NAME
							+ " WHERE TRUE ";


		boolean isFirst = true;


//		if(serviceLevel != null)
//		{
//			queryNormal = queryNormal + " WHERE " + ServiceConfigurationLocal.SERVICE_LEVEL + " = " + serviceLevel;
//
//			isFirst = false;
//		}



//		if(serviceType !=null)
//		{
//			if(isFirst)
//			{
//				queryNormal = queryNormal + " WHERE " + ServiceConfigurationLocal.SERVICE_TYPE + " = " + serviceType;
//
//				isFirst = false;
//
//			}else
//			{
//				queryNormal = queryNormal + " AND " + ServiceConfigurationLocal.SERVICE_TYPE + " = " + serviceType;
//
//			}
//
//		}



		// apply visibility filter


		if(latCenterQuery!=null && lonCenterQuery!=null)
		{

			String queryPartVisibilityFilter = "";




			// filter using Haversine formula using SQL math functions
			queryPartVisibilityFilter = queryPartVisibilityFilter
					+ " (6371.01 * acos(cos( radians("
					+ latCenterQuery
					+ ")) * cos( radians("
					+ ServiceConfigurationLocal.LAT_CENTER
					+ " )) * cos(radians( "
					+ ServiceConfigurationLocal.LON_CENTER
					+ ") - radians("
					+ lonCenterQuery
					+ "))"
					+ " + sin( radians("
					+ latCenterQuery
					+ ")) * sin(radians("
					+ ServiceConfigurationLocal.LAT_CENTER
					+ ")))) <= "
					+ ServiceConfigurationLocal.SERVICE_RANGE ;


			if(isFirst)
			{
				queryNormal = queryNormal + " WHERE ";

				// reset the flag
				isFirst = false;

			}else
			{
				queryNormal = queryNormal + " AND ";
			}


			queryNormal = queryNormal + queryPartVisibilityFilter;
		}

		if(sortBy!=null)
		{
			if(!sortBy.equals(""))
			{
				String queryPartSortBy = " ORDER BY " + sortBy;

				queryNormal = queryNormal + queryPartSortBy;
			}
		}



		if(limit > 0)
		{

			String queryPartLimitOffset = "";

			if(offset>0)
			{
				queryPartLimitOffset = " LIMIT " + limit + " " + " OFFSET " + offset;

			}else
			{
				queryPartLimitOffset = " LIMIT " + limit + " " + " OFFSET " + 0;
			}


			queryNormal = queryNormal + queryPartLimitOffset;
		}



		ArrayList<ServiceConfigurationLocal> servicesList = new ArrayList<ServiceConfigurationLocal>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();


			rs = statement.executeQuery(queryNormal);

			while(rs.next())
			{
				ServiceConfigurationLocal serviceConfigurationLocal = new ServiceConfigurationLocal();

				serviceConfigurationLocal.setRt_distance(rs.getDouble("distance"));

				serviceConfigurationLocal.setServiceID(rs.getInt(ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID));
//				serviceConfigurationLocal.setImagePath(rs.getString(ServiceConfigurationLocal.IMAGE_PATH));
				serviceConfigurationLocal.setLogoImagePath(rs.getString(ServiceConfigurationLocal.LOGO_IMAGE_PATH));
				serviceConfigurationLocal.setBackdropImagePath(rs.getString(ServiceConfigurationLocal.BACKDROP_IMAGE_PATH));

				serviceConfigurationLocal.setServiceName(rs.getString(ServiceConfigurationLocal.SERVICE_NAME));
				serviceConfigurationLocal.setHelplineNumber(rs.getString(ServiceConfigurationLocal.HELPLINE_NUMBER));

				serviceConfigurationLocal.setDescriptionShort(rs.getString(ServiceConfigurationLocal.DESCRIPTION_SHORT));
				serviceConfigurationLocal.setDescriptionLong(rs.getString(ServiceConfigurationLocal.DESCRIPTION_LONG));

				serviceConfigurationLocal.setAddress(rs.getString(ServiceConfigurationLocal.ADDRESS));
				serviceConfigurationLocal.setCity(rs.getString(ServiceConfigurationLocal.CITY));
				serviceConfigurationLocal.setPincode(rs.getLong(ServiceConfigurationLocal.PINCODE));
				serviceConfigurationLocal.setLandmark(rs.getString(ServiceConfigurationLocal.LANDMARK));

				serviceConfigurationLocal.setState(rs.getString(ServiceConfigurationLocal.STATE));
				serviceConfigurationLocal.setCountry(rs.getString(ServiceConfigurationLocal.COUNTRY));
				serviceConfigurationLocal.setISOCountryCode(rs.getString(ServiceConfigurationLocal.ISO_COUNTRY_CODE));
				serviceConfigurationLocal.setISOLanguageCode(rs.getString(ServiceConfigurationLocal.ISO_LANGUAGE_CODE));
				serviceConfigurationLocal.setISOCurrencyCode(rs.getString(ServiceConfigurationLocal.ISO_CURRENCY_CODE));

//				serviceConfigurationLocal.setServiceType(rs.getInt(ServiceConfigurationLocal.SERVICE_TYPE));
//				serviceConfigurationLocal.setServiceLevel(rs.getInt(ServiceConfigurationLocal.SERVICE_LEVEL));

				serviceConfigurationLocal.setLatCenter(rs.getDouble(ServiceConfigurationLocal.LAT_CENTER));
				serviceConfigurationLocal.setLonCenter(rs.getDouble(ServiceConfigurationLocal.LON_CENTER));

				serviceConfigurationLocal.setServiceRange(rs.getInt(ServiceConfigurationLocal.SERVICE_RANGE));

//				serviceConfigurationLocal.setLatMax(rs.getDouble(ServiceConfigurationLocal.LAT_MAX));
//				serviceConfigurationLocal.setLonMax(rs.getDouble(ServiceConfigurationLocal.LON_MAX));
//				serviceConfigurationLocal.setLatMin(rs.getDouble(ServiceConfigurationLocal.LAT_MIN));
//
//				serviceConfigurationLocal.setLonMin(rs.getDouble(ServiceConfigurationLocal.LON_MIN));

				serviceConfigurationLocal.setCreated(rs.getTimestamp(ServiceConfigurationLocal.CREATED));
				serviceConfigurationLocal.setUpdated(rs.getTimestamp(ServiceConfigurationLocal.UPDATED));


				servicesList.add(serviceConfigurationLocal);
				
			}
			

			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}
		finally
		{
			
			try {
					if(rs!=null)
					{rs.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
								
		return servicesList;
	}





	public ServiceConfigurationLocal getServiceConfiguration(Double latCenter, Double lonCenter)
	{
		
		String query = " SELECT "
						+ "6371 * acos(cos( radians("
						+ latCenter + ")) * cos( radians( " + ServiceConfigurationLocal.LAT_CENTER + " ) ) * cos(radians( " + ServiceConfigurationLocal.LON_CENTER + " ) - radians("
						+ lonCenter + "))"
						+ " + sin( radians(" + latCenter + ")) * sin(radians( " + ServiceConfigurationLocal.LAT_CENTER + " ))) as distance" + ","
						+ ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID + ","
						+ ServiceConfigurationLocal.LOGO_IMAGE_PATH + ","
						+ ServiceConfigurationLocal.BACKDROP_IMAGE_PATH + ","

						+ ServiceConfigurationLocal.SERVICE_NAME + ","
						+ ServiceConfigurationLocal.HELPLINE_NUMBER + ","

						+ ServiceConfigurationLocal.DESCRIPTION_SHORT + ","
						+ ServiceConfigurationLocal.DESCRIPTION_LONG + ","

						+ ServiceConfigurationLocal.ADDRESS + ","
						+ ServiceConfigurationLocal.CITY + ","
						+ ServiceConfigurationLocal.PINCODE + ","
						+ ServiceConfigurationLocal.LANDMARK + ","

						+ ServiceConfigurationLocal.STATE + ","
						+ ServiceConfigurationLocal.COUNTRY + ","
						+ ServiceConfigurationLocal.ISO_COUNTRY_CODE + ","

						+ ServiceConfigurationLocal.ISO_LANGUAGE_CODE + ","
						+ ServiceConfigurationLocal.ISO_CURRENCY_CODE + ","

//						+ ServiceConfigurationLocal.SERVICE_TYPE + ","
//						+ ServiceConfigurationLocal.SERVICE_LEVEL + ","

						+ ServiceConfigurationLocal.LAT_CENTER + ","
						+ ServiceConfigurationLocal.LON_CENTER + ","
						+ ServiceConfigurationLocal.SERVICE_RANGE + ","

						+ ServiceConfigurationLocal.CREATED + ","
						+ ServiceConfigurationLocal.UPDATED + ""
						+ " FROM " + ServiceConfigurationLocal.TABLE_NAME
						+ " WHERE " + ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID + " = " + 1;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		ServiceConfigurationLocal serviceConfigurationLocal = null;

		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while(rs.next())
			{
				serviceConfigurationLocal = new ServiceConfigurationLocal();

				serviceConfigurationLocal.setRt_distance(rs.getDouble("distance"));
				serviceConfigurationLocal.setServiceID(rs.getInt(ServiceConfigurationLocal.SERVICE_CONFIGURATION_ID));
//				serviceConfigurationLocal.setImagePath(rs.getString(ServiceConfigurationLocal.IMAGE_PATH));
				serviceConfigurationLocal.setLogoImagePath(rs.getString(ServiceConfigurationLocal.LOGO_IMAGE_PATH));
				serviceConfigurationLocal.setBackdropImagePath(rs.getString(ServiceConfigurationLocal.BACKDROP_IMAGE_PATH));

				serviceConfigurationLocal.setServiceName(rs.getString(ServiceConfigurationLocal.SERVICE_NAME));
				serviceConfigurationLocal.setHelplineNumber(rs.getString(ServiceConfigurationLocal.HELPLINE_NUMBER));

				serviceConfigurationLocal.setDescriptionShort(rs.getString(ServiceConfigurationLocal.DESCRIPTION_SHORT));
				serviceConfigurationLocal.setDescriptionLong(rs.getString(ServiceConfigurationLocal.DESCRIPTION_LONG));

				serviceConfigurationLocal.setAddress(rs.getString(ServiceConfigurationLocal.ADDRESS));
				serviceConfigurationLocal.setCity(rs.getString(ServiceConfigurationLocal.CITY));
				serviceConfigurationLocal.setPincode(rs.getLong(ServiceConfigurationLocal.PINCODE));
				serviceConfigurationLocal.setLandmark(rs.getString(ServiceConfigurationLocal.LANDMARK));

				serviceConfigurationLocal.setState(rs.getString(ServiceConfigurationLocal.STATE));
				serviceConfigurationLocal.setCountry(rs.getString(ServiceConfigurationLocal.COUNTRY));
				serviceConfigurationLocal.setISOCountryCode(rs.getString(ServiceConfigurationLocal.ISO_COUNTRY_CODE));
				serviceConfigurationLocal.setISOLanguageCode(rs.getString(ServiceConfigurationLocal.ISO_LANGUAGE_CODE));
				serviceConfigurationLocal.setISOCurrencyCode(rs.getString(ServiceConfigurationLocal.ISO_CURRENCY_CODE));

//				serviceConfigurationLocal.setServiceType(rs.getInt(ServiceConfigurationLocal.SERVICE_TYPE));
//				serviceConfigurationLocal.setServiceLevel(rs.getInt(ServiceConfigurationLocal.SERVICE_LEVEL));


				serviceConfigurationLocal.setLatCenter(rs.getDouble(ServiceConfigurationLocal.LAT_CENTER));
				serviceConfigurationLocal.setLonCenter(rs.getDouble(ServiceConfigurationLocal.LON_CENTER));

				serviceConfigurationLocal.setServiceRange(rs.getInt(ServiceConfigurationLocal.SERVICE_RANGE));

//				serviceConfigurationLocal.setLatMax(rs.getDouble(ServiceConfigurationLocal.LAT_MAX));
//				serviceConfigurationLocal.setLonMax(rs.getDouble(ServiceConfigurationLocal.LON_MAX));
//				serviceConfigurationLocal.setLatMin(rs.getDouble(ServiceConfigurationLocal.LAT_MIN));
//
//				serviceConfigurationLocal.setLonMin(rs.getDouble(ServiceConfigurationLocal.LON_MIN));

				serviceConfigurationLocal.setCreated(rs.getTimestamp(ServiceConfigurationLocal.CREATED));
				serviceConfigurationLocal.setUpdated(rs.getTimestamp(ServiceConfigurationLocal.UPDATED));

			}
			
			
			//System.out.println("Total itemCategories queried " + itemCategoryList.size());	
	
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		
		{
			
			try {
					if(rs!=null)
					{rs.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return serviceConfigurationLocal;
	}












	private ServiceConfigurationLocal getDefaultConfiguration()
	{
		ServiceConfigurationLocal configuration = new ServiceConfigurationLocal();

		configuration.setAddress("Address not set");
		configuration.setCity("City not set");
		configuration.setCountry("Country not set");
//		configuration.setServiceLevel(GlobalConstants.SERVICE_LEVEL_CITY);

		return  configuration;
	}



}