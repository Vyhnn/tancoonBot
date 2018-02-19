

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class Main {
	
	public static final String token = "MjQ5MTg3MzMwNzMxMTQ3MjY0.C8gAfA.hWgW-CrlvWh4xRxdweoVTlzkweU";
	                                   
	public static JDA jda;
	
	private static final String[] game = new String[]{ "TANCOON", "with Aiden", "Pokemon Uranium", "hide and seek"};

	
	public static void main(String[] args){
		


				
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(new botListener()).buildBlocking();

			jda.getPresence().setGame(Game.of(Game.GameType.DEFAULT, game[(int)(Math.random() * game.length)]));
				
			
		} catch (LoginException | IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			

		
		
	}

}
