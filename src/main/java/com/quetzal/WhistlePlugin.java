package com.quetzal;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Item;
import net.runelite.api.InventoryID;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class WhistlePlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WhistleOverlay overlay;

	@Inject
	private Client client;

	@Inject
	private WhistleConfig config;

	private final Item[] currentInventoryItems = new Item[28];

	private final Deque<WhistleMenuClick> whistleMenuClicks = new ArrayDeque<>();

	private boolean isTryingRecharge = false;
	private boolean isRecharging = false;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event) {
		if (InventoryID.INVENTORY.getId() != event.getContainerId()) return;

		var items = event.getItemContainer().getItems();

		for (int i = 0; i < items.length; i++) {
			var item = items[i];
			var previousItem = currentInventoryItems[i];

			if (previousItem != null) {
				if (item.getId() == -1 && previousItem.getId() != -1) {
					if (isRecharging) {
						isRecharging = false;

						
					}
				}
			}

			currentInventoryItems[i] = item;
		}

		if (isRecharging) {
			isRecharging = false;
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		switch (event.getMenuAction()) {
			case CC_OP: {
				var whistle = WhistleItem.forItemID(event.getItemId());
				if (whistle == null) return;

				final int tick = client.getTickCount();

				var option = event.getMenuOption();
				switch (option) {
					case "Check":
					case "Signal":
					case "Rumour":
						this.whistleMenuClicks.add(new WhistleMenuClick(whistle, tick, option));
						break;
				}
				break;
			}
			case NPC_THIRD_OPTION: {
				if (event.getId() != 1106) return;
				if (!event.getMenuOption().equals("Recharge-whistle")) return;

				isTryingRecharge = true;
				break;
			}

		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {
		switch (event.getType()) {
			case GAMEMESSAGE: {
				var whistleCheck = this.whistleMenuClicks.peekFirst();
				if (whistleCheck == null) return;

				var message = event.getMessage();

				if (whistleCheck.option.equals("Check")) {
					this.whistleMenuClicks.pollFirst();

					var checkMatcher = WhistleMessage.WHISTLE_CHECK.matcher(message);
					if (checkMatcher.matches()) {
						whistleCheck.whistle.charges = Integer.parseInt(checkMatcher.group(1));
					}
				}

				if (whistleCheck.option.equals("Rumour")) {
					this.whistleMenuClicks.pollFirst();

					var rumorMatcher = WhistleMessage.WHISTLE_RUMOR_TARGET.matcher(message);
					if (rumorMatcher.matches()) {
						whistleCheck.whistle.target = WhistleRumorTarget.targetOverlayString(rumorMatcher.group(1));
					}
				}
				break;
			}
			case DIALOG: {
				if (!isTryingRecharge) return;

				var matcher = WhistleMessage.WHISTLE_RECHARGE_CONFIRM.matcher(event.getMessage());
				if (!matcher.matches()) return;

				isRecharging = true;
				isTryingRecharge = false;
			}
			default: {
				log.info("{}", event.getType());
				break;
			}
		}
	}

	@Provides
	WhistleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(WhistleConfig.class);
	}
}
