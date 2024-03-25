package org.nearbyshops.RESTEndpointsOrderHD;

import org.nearbyshops.pushnotificationsdao.DAOOneSignal;
import org.nearbyshops.globals.GlobalConstants;
import org.nearbyshops.globals.Globals;
import org.nearbyshops.model.Order;
import org.nearbyshops.ModelEndpoint.OrderEndPoint;
import org.nearbyshops.ModelRoles.User;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;

import static org.nearbyshops.globals.Globals.oneSignalNotifications;


@Singleton
@Path("/api/Order")
public class OrderResource {


	public OrderResource() {
		super();
		// TODO Auto-generated constructor stub
	}






//
//	@GET
//	@Path("/Notifications/{ShopID}")
//	@Produces(SseFeature.SERVER_SENT_EVENTS)
//	public EventOutput listenToBroadcast(@PathParam("ShopID")int shopID) {
//		final EventOutput eventOutput = new EventOutput();
//
//		if(Globals.broadcasterMap.get(shopID)!=null)
//		{
//			SseBroadcaster broadcasterOne = Globals.broadcasterMap.get(shopID);
//			broadcasterOne.add(eventOutput);
//		}
//		else
//		{
//			SseBroadcaster broadcasterTwo = new SseBroadcaster();
//			broadcasterTwo.add(eventOutput);
//			Globals.broadcasterMap.put(shopID,broadcasterTwo);
//		}
//
//		return eventOutput;
//	}



	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(Order order, @QueryParam("CartID") int cartID)
	{

//		Order orderResult = Globals.orderService.placeOrder(order,cartID);




//		try
//		{




			int orderId = Globals.pladeOrderDAO.placeOrderNew(order, cartID);

			if (orderId != -1) {

				Order orderResult = Globals.orderService.readSingleOrder(orderId);

//			Globals.broadcastMessageToShop("Order Number : " + String.valueOf(orderId) + " Has been received !",orderResult.getShopID());


				oneSignalNotifications.sendNotificationToEndUser(
						orderResult.getEndUserID(),
						GlobalConstants.url_for_notification_icon_value,
						null,
						null,
						10,
						"Order Placed",
						"Your order has been placed successfully !",
						1,
						DAOOneSignal.ORDER_PLACED,
						null
				);





				try
				{

//					System.out.println("Shop ID : " + orderResult.getShopID());

//					int shopAdminID = Globals.daoShopStaff.getAdminIDforShop(orderResult.getShopID());
//					String shopAdminPlayerID = oneSignalNotifications.getPlayerID(shopAdminID).getRt_oneSignalPlayerID();


					String shopAdminPlayerID = oneSignalNotifications.getPlayerIDforShopAdmin(orderResult.getShopID());
					ArrayList<String> playerIDs =  Globals.oneSignalNotifications.getPlayerIDsForShopStaff(orderResult.getShopID(),
							true,null,null,null,null);


					playerIDs.add(shopAdminPlayerID);



					Globals.oneSignalNotifications.sendNotificationToUser(
							playerIDs,
							GlobalConstants.ONE_SIGNAL_APP_ID_SHOP_OWNER_APP,
							GlobalConstants.ONE_SIGNAL_API_KEY_SHOP_OWNER_APP,
							GlobalConstants.url_for_notification_icon_value,
							null,
							null,
							10,
							"Order Received",
							"Your have received an order !",
							1,
							DAOOneSignal.ORDER_PLACED,
							null
					);



				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}




				return Response.status(Status.CREATED)
//					.entity(orderResult)
						.build();


			} else {

				//Response.status(Status.CREATED).location(arg0)

				return Response.status(Status.NOT_MODIFIED)
						.build();
			}

//		}
//		catch (Exception ex)
//		{
//
//			ex.printStackTrace();
//
//		}
//		finally {
//
//			return Response.status(Status.NOT_MODIFIED)
//					.build();
//
//		}
		
	}








//	@RolesAllowed({GlobalConstants.ROLE_END_USER,GlobalConstants.ROLE_ADMIN})


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_END_USER,GlobalConstants.ROLE_ADMIN})
	public Response getOrders(
			@QueryParam("OrderID")Integer orderID,
			@QueryParam("ShopID")Integer shopID,
			@QueryParam("FilterByUserID")boolean filterByUserID,
			@QueryParam("PickFromShop") Boolean pickFromShop,
			@QueryParam("StatusHomeDelivery")Integer homeDeliveryStatus,
			@QueryParam("StatusPickFromShopStatus")Integer pickFromShopStatus,
			@QueryParam("DeliveryGuyID")Integer deliveryGuyID,
			@QueryParam("PaymentsReceived") Boolean paymentsReceived,
			@QueryParam("DeliveryReceived") Boolean deliveryReceived,
			@QueryParam("latCenter")Double latCenter, @QueryParam("lonCenter")Double lonCenter,
			@QueryParam("PendingOrders") Boolean pendingOrders,
			@QueryParam("SearchString") String searchString,
			@QueryParam("SortBy") String sortBy,
			@QueryParam("Limit")Integer limit, @QueryParam("Offset")Integer offset,
			@QueryParam("GetRowCount")boolean getRowCount,
			@QueryParam("MetadataOnly")boolean getOnlyMetaData
	)
	{
		//							  @QueryParam("EndUserID")Integer endUserID,




		// *********************** second Implementation

//		User user = (User) Globals.accountApproved;

//		if(user.getRole()==GlobalConstants.ROLE_END_USER_CODE)
//		{
//
//			Shop shop = Globals.shopDAO.getShopIDForShopAdmin(shopAdmin.getShopAdminID());
//			shopID = shop.getShopID();
//			endUserID = endUser.getEndUserID();
//		}
//		else
//		{
//			throw new ForbiddenException("Not Permitted !");
//		}


//		System.out.println("Order Endpoint fetch !");



		try
		{





				Integer endUserID = null;

				if(filterByUserID)
				{
					endUserID = ((User)Globals.accountApproved).getUserID();
				}






					if(limit!=null)
					{
						if(limit >= GlobalConstants.max_limit)
						{
							limit = GlobalConstants.max_limit;
						}

						if(offset==null)
						{
							offset = 0;
						}
					}


					getRowCount=true;



					OrderEndPoint endpoint = Globals.orderService.readOrders(orderID,
							endUserID,shopID, pickFromShop,
							homeDeliveryStatus,pickFromShopStatus,
							deliveryGuyID,
							latCenter,lonCenter,
							pendingOrders,
							searchString,
							sortBy,limit,offset,
							getRowCount,getOnlyMetaData);




					if(limit!=null)
					{
						endpoint.setLimit(limit);
						endpoint.setOffset(offset);
						endpoint.setMax_limit(GlobalConstants.max_limit);
					}



					return Response.status(Status.OK)
							.entity(endpoint)
							.build();


		}
		catch (Exception e)
		{

			e.printStackTrace();
		}




		return null;



//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		//Marker

	}








	// requires authentication by the Distributor
	@PUT
	@Path("/CancelByUser/{OrderID}")
	@RolesAllowed({GlobalConstants.ROLE_END_USER})
	public Response cancelledByShop(@PathParam("OrderID")int orderID)
	{
		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;


//		if(user.getRole()==GlobalConstants.ROLE_END_USER_CODE)
//		{
//			EndUser endUser = (EndUser) Globals.accountApproved;
//
//			if(order.getEndUserID()!=endUser.getEndUserID())
//			{
//				throw new ForbiddenException("Not Permitted !");
//			}
//		}
//		else
//		{
//			throw new ForbiddenException("Not Permitted !");
//		}



		int rowCount = Globals.orderService.orderCancelledByEndUser(orderID);

		if(rowCount >= 1)
		{

			return Response.status(Status.OK)
					.entity(null)
					.build();
		}
		if(rowCount <= 0)
		{

			return Response.status(Status.NOT_MODIFIED)
					.entity(null)
					.build();
		}

		return null;
	}




//
//	@PUT
//	@Path("/{OrderID}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateOrder(@PathParam("OrderID")int orderID, Order order)
//	{
//
//		order.setOrderID(orderID);
//
//		int rowCount = Globals.orderService.updateOrder(order);
//
//
//		if(rowCount >= 1)
//		{
//
//			return Response.status(Status.OK)
//					.entity(null)
//					.build();
//		}
//		if(rowCount <= 0)
//		{
//
//			return Response.status(Status.NOT_MODIFIED)
//					.entity(null)
//					.build();
//		}
//
//
//		return null;
//	}
//
//
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateOrderBulk(List<Order> ordersList)
//	{
//
//		int rowCount = 0;
//
//		for(Order orderItem: ordersList)
//		{
//			System.out.println("UPDATE BULK ORDER : DELIVERY GUY DELIVERY_GUY_SELF_ID : " + orderItem.getDeliveryGuySelfID());
//			rowCount = rowCount + Globals.orderService.updateOrder(orderItem);
//		}
//
//
//
//
//		if(rowCount <= 0)
//		{
//			Response response = Response.status(Status.NOT_MODIFIED)
//					.entity(null)
//					.build();
//
//			return response;
//		}
//		else if(rowCount >= ordersList.size())
//		{
//			Response response = Response.status(Status.OK)
//					.entity(null)
//					.build();
//
//			return response;
//		}
//
//
//		return null;
//	}
//
//
//
//	// requires authentication by the Distributor
//	@PUT
//	@Path("/ReturnOrder/{OrderID}")
//	public Response returnOrder(@PathParam("OrderID")int orderID)
//	{
//
//		int rowCount = Globals.orderService.returnOrderByDeliveryGuy(orderID);
//
//		if(rowCount >= 1)
//		{
//
//			return Response.status(Status.OK)
//					.entity(null)
//					.build();
//		}
//		if(rowCount <= 0)
//		{
//
//			return Response.status(Status.NOT_MODIFIED)
//					.entity(null)
//					.build();
//		}
//
//		return null;
//	}
//
//


	//@QueryParam("GetDeliveryAddress")Boolean getDeliveryAddress,
//	@QueryParam("GetStats")Boolean getStats,
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getOrders(@QueryParam("OrderID")Integer orderID,
//							  @QueryParam("EndUserID")Integer endUserID,
//							  @QueryParam("ShopID")Integer shopID,
//							  @QueryParam("PickFromShop") Boolean pickFromShop,
//							  @QueryParam("StatusHomeDelivery")Integer homeDeliveryStatus,
//							  @QueryParam("StatusPickFromShopStatus")Integer pickFromShopStatus,
//							  @QueryParam("DeliveryGuyID")Integer deliveryGuyID,
//							  @QueryParam("PaymentsReceived") Boolean paymentsReceived,
//							  @QueryParam("DeliveryReceived") Boolean deliveryReceived,
//							  @QueryParam("latCenter")Double latCenter, @QueryParam("lonCenter")Double lonCenter,
//							  @QueryParam("PendingOrders") Boolean pendingOrders,
//							  @QueryParam("SortBy") String sortBy,
//							  @QueryParam("Limit")Integer limit, @QueryParam("Offset")Integer offset,
//							  @QueryParam("metadata_only")Boolean metaonly)
//
//	{
//
//		// *********************** second Implementation
//
//		int set_limit = 30;
//		int set_offset = 0;
//		final int max_limit = 100;
//
//		if(limit!= null)
//		{
//
//			if (limit >= max_limit) {
//
//				set_limit = max_limit;
//			}
//			else
//			{
//
//				set_limit = limit;
//			}
//
//		}
//
//		if(offset!=null)
//		{
//			set_offset = offset;
//		}
//
//		OrderEndPointPFS endPoint = Globals.orderService.endPointMetaDataOrders(orderID,
//				endUserID,shopID, pickFromShop,
//				homeDeliveryStatus,pickFromShopStatus,
//				deliveryGuyID,
//				paymentsReceived,deliveryReceived,pendingOrders
//				);
//
//		endPoint.setLimit(set_limit);
//		endPoint.setMax_limit(max_limit);
//		endPoint.setOffset(set_offset);
//
//		List<Order> list = null;
//
//
//		if(metaonly==null || (!metaonly)) {
//
//			list =
//					Globals.orderService.readOrders(orderID,
//							endUserID,shopID, pickFromShop,
//							homeDeliveryStatus,pickFromShopStatus,
//							deliveryGuyID,
//							paymentsReceived,deliveryReceived,
//							latCenter,lonCenter,
//							pendingOrders,
//							sortBy,limit,offset);
//
//
//			/*
//
//			if(getDeliveryAddress!=null && getDeliveryAddress)
//			{
//				for(Order order: list)
//				{
//					order.setDeliveryAddress(
//							Globals.deliveryAddressService
//									.readAddress(order.getDeliveryAddressID())
//					);
//				}
//
//			}
//*/
//
///*
//
//			if(getStats!=null && getStats) {
//
//				for (Order order : list) {
//
//					order.setOrderStats(Globals.orderItemService.getOrderStats(order.getOrderID()));
//				}
//
//			}
//*/
//
//
//			endPoint.setResults(list);
//		}
//
//
///*
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//*/
//
//		//Marker
//
//		return Response.status(Status.OK)
//				.entity(endPoint)
//				.build();
//
//
//	}
//
//
//
//
//
//	@GET
//	@Path("/{OrderID}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getOrder(@PathParam("OrderID")int orderID)
//	{
//
//		Order order = Globals.orderService.readSingleOrder(orderID);
//
//		if(order != null)
//		{
//			Response response = Response.status(Status.OK)
//					.entity(order)
//					.build();
//
//			return response;
//
//		} else
//		{
//
//			Response response = Response.status(Status.NO_CONTENT)
//					.entity(order)
//					.build();
//
//			return response;
//
//		}
//
//	}




/*
	@GET
	@Path("/Stats/{OrderID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderStats(@PathParam("OrderID")int orderID)
	{

		OrderStats orderStats = Globals.orderItemService.getOrderStats(orderID);

		if(orderStats != null)
		{
			Response response = Response.status(Status.OK)
					.entity(orderStats)
					.build();

			return response;

		} else
		{

			Response response = Response.status(Status.NO_CONTENT)
					.entity(orderStats)
					.build();

			return response;

		}

	}*/

}
