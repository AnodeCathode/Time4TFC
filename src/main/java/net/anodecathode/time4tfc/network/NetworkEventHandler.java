package net.anodecathode.time4tfc.network;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import net.anodecathode.time4tfc.data.SessionData;
import net.anodecathode.time4tfc.time4tfc;
import net.anodecathode.time4tfc.network.packet.PacketServerSettings;

import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author dmillerw
 */
public class NetworkEventHandler {

	public static void register() {
		FMLCommonHandler.instance().bus().register(new NetworkEventHandler());
	}

	@SubscribeEvent
	public void onClientLogin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		if (!event.isLocal()) {
			SessionData.modEnabled = false;
		}
	}

	@SubscribeEvent
	public void onClientLogout(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		SessionData.loadFromConfiguration(time4tfc.configuration);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		PacketHandler.INSTANCE.sendTo(new PacketServerSettings(), (EntityPlayerMP) event.player);
	}
}
