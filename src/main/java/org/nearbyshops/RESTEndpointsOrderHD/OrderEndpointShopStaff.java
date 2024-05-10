package org.nearbyshops.RESTEndpointsOrderHD;

import org.nearbyshops.pushnotificationsdao.DAOOneSignal;
import org.nearbyshops.globals.GlobalConstants;
import org.nearbyshops.globals.Globals;
import org.nearbyshops.globals.SendSMS;
import org.nearbyshops.model.Order;
import org.nearbyshops.model.Shop;
import org.nearbyshops.ModelEndpoint.OrderEndPoint;
import org.nearbyshops.ModelRoles.Endpoints.UserEndpoint;
import org.nearbyshops.ModelRoles.ShopStaffPermissions;
import org.nearbyshops.ModelRoles.User;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

import static org.nearbyshops.globals.Globals.getMailerInstance;
import static org.nearbyshops.globals.Globals.oneSignalNotifications;


@Singleton
@Path("/api/Order/ShopStaff")
public class OrderEndpointShopStaff {


	public OrderEndpointShopStaff() {
		super();
		// TODO Auto-generated constructor stub
	}






//
//	@GET
//	@Path("/Notifications/{ShopID}")
//	@Produces(SseFeature.SERVER_SENT_EVENTS)
//	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN})
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








	@PUT
	@Path("/SetConfirmed/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response confirmOrder(@PathParam("OrderID")int orderID)
	{

//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;


 		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitConfirmOrders())
			{
				throw new ForbiddenException("Not Permitted !");
			}

		}






		int rowCount = Globals.daoOrderStaff.confirmOrder(orderID);

		if(rowCount >= 1)
		{

			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Confirmed",
					"Order number " + String.valueOf(orderID) + " has been confirmed !",
					1,
					DAOOneSignal.ORDER_CONFIRMED,
					null
			);




////			String shopAdminPlayerID = oneSignalNotifications.getPlayerIDforShopAdmin(orderResult.getShopID());
//			ArrayList<String> playerIDs =  Globals.oneSignalNotifications.getPlayerIDsForShopStaff(orderResult.getShopID(),
//					null,true,null,null,null);
//
//
////			playerIDs.add(shopAdminPlayerID);
//
//
//
//			Globals.oneSignalNotifications.sendNotificationToUser(
//					playerIDs,
//					GlobalConstants.ONE_SIGNAL_APP_ID_SHOP_OWNER_APP,
//					GlobalConstants.ONE_SIGNAL_API_KEY_SHOP_OWNER_APP,
//					GlobalConstants.url_for_notification_icon_value,
//					null,
//					null,
//					10,
//					"Order Confirmed",
//					"Order number " + String.valueOf(orderID) + " has been confirmed !",
//					1,
//					DAOOneSignal.ORDER_PLACED,
//					null
//			);



			return Response.status(Status.OK)
					.build();
		}



		return Response.status(Status.NOT_MODIFIED)
				.build();


	}






	@PUT
	@Path("/SetOrderPacked/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response setOrderPacked(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);
		User user = (User) Globals.accountApproved;



		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());


			if(!permissions.isPermitSetOrdersPacked())
			{
				throw new ForbiddenException("Not Permitted !");
			}
		}



//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}


//			order.setStatusHomeDelivery(OrderStatusHomeDelivery.ORDER_PACKED);

			int rowCount = Globals.daoOrderStaff.setOrderPacked(orderID);


			if(rowCount >= 1)
			{

				Order orderResult = Globals.orderService.readSingleOrder(orderID);

				oneSignalNotifications.sendNotificationToEndUser(
						orderResult.getEndUserID(),
						GlobalConstants.url_for_notification_icon_value,
						null,
						null,
						10,
						"Order Packed",
						"Order number " + String.valueOf(orderID) + " has been Packed !",
						1,
						DAOOneSignal.ORDER_PACKED,
						null
				);



//
////			String shopAdminPlayerID = oneSignalNotifications.getPlayerIDforShopAdmin(orderResult.getShopID());
//				ArrayList<String> playerIDs =  Globals.oneSignalNotifications.getPlayerIDsForShopStaff(orderResult.getShopID(),
//						null,null,true,null,null);
//
//
////			playerIDs.add(shopAdminPlayerID);
//
//
//
//				Globals.oneSignalNotifications.sendNotificationToUser(
//						playerIDs,
//						GlobalConstants.ONE_SIGNAL_APP_ID_SHOP_OWNER_APP,
//						GlobalConstants.ONE_SIGNAL_API_KEY_SHOP_OWNER_APP,
//						"https://i1.wp.com/nearbyshops.org/wp-content/uploads/2017/02/cropped-backdrop_play_store-1.png?w=250&ssl=1",
//						null,
//						null,
//						10,
//						"Order Packed",
//						"Order number " + String.valueOf(orderID) + " has been packed !",
//						1,
//						DAOOneSignal.ORDER_PACKED,
//						null
//				);



				return Response.status(Status.OK)
						.entity(null)
						.build();
			}


		return Response.status(Status.NOT_MODIFIED)
				.build();


	}




	@PUT
	@Path("/HandoverToDelivery/{DeliveryGuySelfID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN,GlobalConstants.ROLE_SHOP_STAFF})
	public Response handoverToDelivery(@PathParam("DeliveryGuySelfID")int deliveryGuyID, List<Order> ordersList)
	{

//		User user = (User) Globals.accountApproved;

		int rowCount = 0;
//		int shopID = 0;


		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitHandoverToDelivery())
			{
				throw new ForbiddenException("Not Permitted !");
			}
		}




		for(Order orderReceived : ordersList)
		{

//			Order order = Globals.orderService.readStatusHomeDelivery(orderReceived.getOrderID());


//			order.setStatusHomeDelivery(OrderStatusHomeDelivery.HANDOVER_REQUESTED);
//			order.setDeliveryGuySelfID(deliveryGuyID);

			rowCount = Globals.daoOrderStaff.handoverToDelivery(orderReceived.getOrderID(),deliveryGuyID) + rowCount;

		}





		if(rowCount==ordersList.size())
		{


//			String shopAdminPlayerID = oneSignalNotifications.getPlayerIDforShopAdmin(orderResult.getShopID());
//			ArrayList<String> playerIDs =  Globals.oneSignalNotifications.getPlayerIDsForShopStaff(orderResult.getShopID(),
//					null,null,true,null,null);


//			playerIDs.add(shopAdminPlayerID);



			Globals.oneSignalNotifications.sendNotificationToUser(
					deliveryGuyID,
					GlobalConstants.ONE_SIGNAL_APP_ID_SHOP_OWNER_APP,
					GlobalConstants.ONE_SIGNAL_API_KEY_SHOP_OWNER_APP,
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Handover Requested",
					"Handover requested for some orders !",
					1,
					DAOOneSignal.ORDER_PACKED,
					null
			);


			return Response.status(Status.OK)
					.build();

		}
		else if (rowCount>0 && rowCount<ordersList.size())
		{
			return Response.status(Status.PARTIAL_CONTENT)
					.build();

		}
		else
		{
			return Response.status(Status.NOT_MODIFIED)
					.build();
		}
	}





	@PUT
	@Path("/UndoHandover/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response undoHandover(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitHandoverToDelivery())
			{
				throw new ForbiddenException("Not Permitted !");
			}
		}


		int rowCount = Globals.daoOrderStaff.undoHandover(orderID);



		if(rowCount >= 1)
		{

			return Response.status(Status.OK)
					.entity(null)
					.build();
		}



		return Response.status(Status.NOT_MODIFIED)
				.build();

//		order.setOrderID(orderID);
//		int rowCount = Globals.orderService.updateOrder(order);


	}




	@PUT
	@Path("/AcceptReturn/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response acceptReturn(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitAcceptReturns())
			{
				throw new ForbiddenException("Not Permitted !");
			}

		}


		int rowCount = Globals.daoOrderStaff.acceptReturn(orderID);




//
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}




		if(rowCount >= 1)
		{
			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Returned",
					"Order number " + String.valueOf(orderID) + " has been returned because order failed to be delivered !",
					1,
					DAOOneSignal.ORDER_RETURNED,
					null
			);


			return Response.status(Status.OK)
					.build();


		}


		return Response.status(Status.NOT_MODIFIED)
				.build();

//		order.setOrderID(orderID);
//		int rowCount = Globals.orderService.updateOrder(order);

	}




	@PUT
	@Path("/UnpackOrder/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response unpackOrder(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitAcceptReturns())
			{
				throw new ForbiddenException("Not Permitted !");
			}

		}


		int rowCount = Globals.daoOrderStaff.unpackOrder_delete(orderID);


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(rowCount >= 1)
		{

			return Response.status(Status.OK)
					.entity(null)
					.build();
		}


		return Response.status(Status.NOT_MODIFIED)
				.build();

//		order.setOrderID(orderID);
//		int rowCount = Globals.orderService.updateOrder(order);

	}








	@PUT
	@Path("/PaymentReceived/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response paymentReceived(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitAcceptPaymentsFromDelivery())
			{
				throw new ForbiddenException("Not Permitted !");
			}

		}


		int rowCount = Globals.daoOrderStaff.paymentReceived(orderID);


		if(rowCount >= 1)
		{

			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Delivered",
					"Order number " + String.valueOf(orderID) + " has been delivered to you !",
					1,
					DAOOneSignal.ORDER_DELIVERED,
					null
			);



			return Response.status(Status.OK)
					.entity(null)
					.build();
		}


		return Response.status(Status.NOT_MODIFIED)
				.build();

//		order.setOrderID(orderID);
//		int rowCount = Globals.orderService.updateOrder(order);

	}








	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response getOrders(@QueryParam("OrderID")Integer orderID,
                              @QueryParam("EndUserID")Integer endUserID,
                              @QueryParam("ShopID")Integer shopID,
                              @QueryParam("PickFromShop") Boolean pickFromShop,
                              @QueryParam("StatusHomeDelivery")Integer homeDeliveryStatus,
                              @QueryParam("StatusPickFromShopStatus")Integer pickFromShopStatus,
                              @QueryParam("DeliveryGuyID")Integer deliveryGuyID,
                              @QueryParam("latCenter")Double latCenter, @QueryParam("lonCenter")Double lonCenter,
                              @QueryParam("PendingOrders") Boolean pendingOrders,
                              @QueryParam("SearchString") String searchString,
                              @QueryParam("SortBy") String sortBy,
                              @QueryParam("Limit")Integer limit, @QueryParam("Offset")Integer offset,
							  @QueryParam("GetRowCount")boolean getRowCount,
							  @QueryParam("MetadataOnly")boolean getOnlyMetaData)

	{



		// *********************** second Implementation

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_ADMIN_CODE)
		{
			Shop shop = Globals.shopDAO.getShopIDForShopAdmin(user.getUserID());
			shopID = shop.getShopID();
		}
		else if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			shopID = Globals.daoShopStaff.getShopIDforShopStaff(user.getUserID());
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
				true,getOnlyMetaData);




		if(limit!=null)
		{
			endpoint.setLimit(limit);
			endpoint.setOffset(offset);
			endpoint.setMax_limit(GlobalConstants.max_limit);
		}





//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		//Marker

		return Response.status(Status.OK)
				.entity(endpoint)
				.build();
	}







	@GET
	@Path("/FetchDeliveryGuys")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response fetchDeliveryGuys(
			@QueryParam("StatusHomeDelivery")Integer homeDeliveryStatus,
			@QueryParam("SortBy") String sortBy,
			@QueryParam("Limit")Integer limit, @QueryParam("Offset")Integer offset,
			@QueryParam("GetRowCount")boolean getRowCount,
			@QueryParam("MetadataOnly")boolean getOnlyMetaData

	)
	{

		//		@QueryParam("ShopID")Integer shopID,


		int shopID = 0;

		// *********************** second Implementation

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_ADMIN_CODE)
		{
			Shop shop = Globals.shopDAO.getShopIDForShopAdmin(user.getUserID());
			shopID = shop.getShopID();
		}
		else if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			shopID = Globals.daoShopStaff.getShopIDforShopStaff(user.getUserID());
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


		UserEndpoint endpoint = Globals.daoOrderStaff.fetchDeliveryGuys(
				shopID,
				homeDeliveryStatus,
				sortBy,limit,offset,
				true,getOnlyMetaData
		);






		if(limit!=null)
		{
			endpoint.setLimit(limit);
			endpoint.setOffset(offset);
			endpoint.setMax_limit(GlobalConstants.max_limit);
		}




//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		//Marker

		return Response.status(Status.OK)
				.entity(endpoint)
				.build();
	}






	@PUT
	@Path("/SetConfirmedPFS/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response confirmOrderPFS(@PathParam("OrderID")int orderID)
	{

//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;


		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitConfirmOrders())
			{
				throw new ForbiddenException("Not Permitted !");
			}

		}







		int rowCount = Globals.daoOrderStaff.confirmOrderPFS(orderID);

		if(rowCount >= 1)
		{
			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Confirmed",
					"Order number " + String.valueOf(orderID) + " has been confirmed !",
					1,
					DAOOneSignal.ORDER_CONFIRMED,
					null
			);


//			String htmlText = "";


			User endUserProfile = orderResult.getRt_end_user_profile();


			if(endUserProfile.getEmail()!=null)
			{
				String htmlText = "<h2>Your Order with Order Number : " + orderID + " is confirmed by the seller.</h2>" +
						"<p>We will let you know when its packed and ready for pickup. <p>";



				Email emailComposed = EmailBuilder.startingBlank()
						.from(GlobalConstants.EMAIL_SENDER_NAME, GlobalConstants.EMAIL_ADDRESS_FOR_SENDER)
						.to(orderResult.getRt_end_user_profile().getName(),orderResult.getRt_end_user_profile().getEmail())
						.withSubject("Order No. " + orderID + " Confirmed")
						.withHTMLText(htmlText)
						.buildEmail();




				getMailerInstance().sendMail(emailComposed,true);
			}

			return Response.status(Status.OK)
					.build();
		}



		return Response.status(Status.NOT_MODIFIED)
				.build();


	}




	@PUT
	@Path("/SetOrderPackedPFS/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response setOrderPackedPFS(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);
		User user = (User) Globals.accountApproved;



		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());


			if(!permissions.isPermitSetOrdersPacked())
			{
				throw new ForbiddenException("Not Permitted !");
			}
		}



//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}


//			order.setStatusHomeDelivery(OrderStatusHomeDelivery.ORDER_PACKED);

		int rowCount = Globals.daoOrderStaff.setOrderPackedPFS(orderID);


		if(rowCount >= 1)
		{
			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Packed",
					"Order number " + String.valueOf(orderID) + " has been packed !",
					1,
					DAOOneSignal.ORDER_PACKED,
					null
			);



			User endUserProfile = orderResult.getRt_end_user_profile();


			if(endUserProfile.getEmail()!=null)
			{
				String htmlText = "<h2>Your Order with Order Number : " + orderID + " is packed by the seller.</h2>" +
						"<p>We will let you know when its ready for pickup. <p>";



				Email emailComposed = EmailBuilder.startingBlank()
						.from(GlobalConstants.EMAIL_SENDER_NAME, GlobalConstants.EMAIL_ADDRESS_FOR_SENDER)
						.to(orderResult.getRt_end_user_profile().getName(),orderResult.getRt_end_user_profile().getEmail())
						.withSubject("Order No. " + orderID + " Packed")
						.withHTMLText(htmlText)
						.buildEmail();




				getMailerInstance().sendMail(emailComposed,true);
			}



			return Response.status(Status.OK)
					.entity(null)
					.build();
		}

		return Response.status(Status.NOT_MODIFIED)
				.build();


	}




	@PUT
	@Path("/SetOrderReadyForPickupPFS/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response setOrderReadyForPickupPFS(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);
		User user = (User) Globals.accountApproved;



		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());


			if(!permissions.isPermitSetOrdersPacked())
			{
				throw new ForbiddenException("Not Permitted !");
			}
		}



//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}


//			order.setStatusHomeDelivery(OrderStatusHomeDelivery.ORDER_PACKED);

		int rowCount = Globals.daoOrderStaff.setOrderReadyForPickupPFS(orderID);


		if(rowCount >= 1)
		{
			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Ready for Pickup",
					"Order number " + String.valueOf(orderID) + " is ready for pickup !",
					1,
					DAOOneSignal.ORDER_PACKED,
					null
			);





			User endUserProfile = orderResult.getRt_end_user_profile();


			if(endUserProfile.getEmail()!=null)
			{
				String htmlText = "<h2>Your Order with Order Number : " + orderID + " is Ready for Pickup.</h2>" +
						"<p>You can collect the items from the shop premises. The address for shop is provided in the shop details.  " +
						" We advice you to call the shop and confirm timing and availability for pickup before you reach the shop.<p>";



				Email emailComposed = EmailBuilder.startingBlank()
						.from(GlobalConstants.EMAIL_SENDER_NAME, GlobalConstants.EMAIL_ADDRESS_FOR_SENDER)
						.to(orderResult.getRt_end_user_profile().getName(),orderResult.getRt_end_user_profile().getEmail())
						.withSubject("Order No. " + orderID + " is Ready for Pickup")
						.withHTMLText(htmlText)
						.buildEmail();




				getMailerInstance().sendMail(emailComposed,true);
			}



			if(endUserProfile.getPhone()!=null)
			{
				SendSMS.sendSMS("Order No. " + orderID + " is Ready for Pickup. You can collect the order from the Shop !",
						endUserProfile.getPhone());
			}



			return Response.status(Status.OK)
					.entity(null)
					.build();
		}

		return Response.status(Status.NOT_MODIFIED)
				.build();


	}







	@PUT
	@Path("/PaymentReceivedPFS/{OrderID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ROLE_SHOP_ADMIN, GlobalConstants.ROLE_SHOP_STAFF})
	public Response paymentReceivedPFS(@PathParam("OrderID")int orderID)
	{
//		Order order = Globals.orderService.readStatusHomeDelivery(orderID);

		User user = (User) Globals.accountApproved;

		if(user.getRole()==GlobalConstants.ROLE_SHOP_STAFF_CODE)
		{
			ShopStaffPermissions permissions = Globals.daoShopStaff.getShopStaffPermissions(user.getUserID());

			if(!permissions.isPermitAcceptPaymentsFromDelivery())
			{
				throw new ForbiddenException("Not Permitted !");
			}

		}




		int rowCount = Globals.daoOrderStaff.paymentReceivedPFS(orderID);


		if(rowCount >= 1)
		{
			Order orderResult = Globals.orderService.readSingleOrder(orderID);

			oneSignalNotifications.sendNotificationToEndUser(
					orderResult.getEndUserID(),
					GlobalConstants.url_for_notification_icon_value,
					null,
					null,
					10,
					"Order Delivered",
					"Order number " + String.valueOf(orderID) + " has been delivered to you and order amount has been paid !",
					1,
					DAOOneSignal.ORDER_DELIVERED,
					null
			);




			User endUserProfile = orderResult.getRt_end_user_profile();


			if(endUserProfile.getEmail()!=null)
			{
				String htmlText = "<h2>Your Order with Order Number : " + orderID + " is Delivered.</h2>" +
						"<p>How was your experience ? Having any issues ... please let us know !" +
						" For support and feedback please call market helpline provided in the market details section !";



				Email emailComposed = EmailBuilder.startingBlank()
						.from(GlobalConstants.EMAIL_SENDER_NAME, GlobalConstants.EMAIL_ADDRESS_FOR_SENDER)
						.to(orderResult.getRt_end_user_profile().getName(),orderResult.getRt_end_user_profile().getEmail())
						.withSubject("Order No. " + orderID + " is Delivered")
						.withHTMLText(htmlText)
						.buildEmail();


				getMailerInstance().sendMail(emailComposed,true);
			}





			if(endUserProfile.getPhone()!=null)
			{
				SendSMS.sendSMS("Order No. " + orderID + " is Delivered to you ! If you have any issues with the order feel free to contact market administrator !",
						endUserProfile.getPhone());
			}



			return Response.status(Status.OK)
					.build();
		}




		return Response.status(Status.NOT_MODIFIED)
				.build();

//		order.setOrderID(orderID);
//		int rowCount = Globals.orderService.updateOrder(order);

	}




	// Permissions : General
	// Submit Item Categories
	// Submit Items
	// Add / Remove Items From Shop
	// Update Stock

	// Permissions : Home Delivery Inventory
	// 0. Cancel OrderPFS's
	// 1. Confirm OrderPFS's
	// 2. Set OrderPFS's Packed
	// 3. Handover to Delivery
	// 4. Mark OrderPFS Delivered
	// 5. Payment Received | Collect Payments from Delivery Guy
	// 6. Accept Return's | Cancelled By Shop

	// 7. Accept Return | Returned by Delivery Guy | Not required

}
