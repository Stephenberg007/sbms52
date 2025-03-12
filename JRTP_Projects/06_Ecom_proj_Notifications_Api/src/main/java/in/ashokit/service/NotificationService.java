package in.ashokit.service;

import in.ashokit.dto.WatiResponse;

public interface NotificationService {
	public Integer sendDeliveryNotifications();
	public Integer sendNotificationToPendingOrders();

}
