package test;

import java.util.List;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App {
	private static String token = "Discord-token";
	public static void main(String[] args) {
		MessageWriter messagewriter = new MessageWriter();
		
		try {
			JDABuilder.createLight(token)
			.addEventListeners(messagewriter)
			.enableIntents(
					List.of(
							GatewayIntent.GUILD_MESSAGES,
								GatewayIntent.MESSAGE_CONTENT
						)
					)
			.setStatus(OnlineStatus.ONLINE)
			.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
