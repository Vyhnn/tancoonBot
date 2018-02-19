
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

public class botListener extends ListenerAdapter{

	final int SHINYRATE = 20;
	final int STEPCOUNT = 50;
	String[] Tancoons = new String[] {"tancoon", "tanscure", "tansheen"};
	static String[] move = new String[] {"MEGAHORN", "ATTACKORDER", "BUGBUZZ", "XSCISSOR", "SIGNALBEAM", "UTURN", "STEAMROLLER", "BUGBITE", "SILVERWIND", "STRUGGLEBUG", "TWINEEDLE", "FURYCUTTER", "LEECHLIFE", "PINMISSILE", "DEFENDORDER", "HEALORDER", "QUIVERDANCE", "RAGEPOWDER", "SPIDERWEB", "STRINGSHOT", "TAILGLOW", "FOULPLAY", "NIGHTDAZE", "CRUNCH", "DARKPULSE", "SUCKERPUNCH", "NIGHTSLASH", "BITE", "FEINTATTACK", "SNARL", "ASSURANCE", "PAYBACK", "PURSUIT", "THIEF", "KNOCKOFF", "BEATUP", "FLING", "PUNISHMENT", "DARKVOID", "EMBARGO", "FAKETEARS", "FLATTER", "HONECLAWS", "MEMENTO", "NASTYPLOT", "QUASH", "SNATCH", "SWITCHEROO", "TAUNT", "TORMENT", "ROAROFTIME", "DRACOMETEOR", "OUTRAGE", "DRAGONRUSH", "SPACIALREND", "DRAGONPULSE", "DRAGONCLAW", "DRAGONTAIL", "DRAGONBREATH", "DUALCHOP", "TWISTER", "DRAGONRAGE", "DRAGONDANCE", "BOLTSTRIKE", "THUNDER", "VOLTTACKLE", "ZAPCANNON", "FUSIONBOLT", "THUNDERBOLT", "WILDCHARGE", "DISCHARGE", "THUNDERPUNCH", "VOLTSWITCH", "SPARK", "THUNDERFANG", "SHOCKWAVE", "ELECTROWEB", "CHARGEBEAM", "THUNDERSHOCK", "ELECTROBALL", "CHARGE", "MAGNETRISE", "THUNDERWAVE", "FOCUSPUNCH", "HIJUMPKICK", "CLOSECOMBAT", "FOCUSBLAST", "SUPERPOWER", "CROSSCHOP", "DYNAMICPUNCH", "HAMMERARM", "JUMPKICK", "AURASPHERE", "SACREDSWORD", "SECRETSWORD", "SKYUPPERCUT", "SUBMISSION", "BRICKBREAK", "DRAINPUNCH", "VITALTHROW", "CIRCLETHROW", "FORCEPALM", "LOWSWEEP", "REVENGE", "ROLLINGKICK", "WAKEUPSLAP", "KARATECHOP", "MACHPUNCH", "ROCKSMASH", "STORMTHROW", "VACUUMWAVE", "DOUBLEKICK", "ARMTHRUST", "TRIPLEKICK", "COUNTER", "FINALGAMBIT", "LOWKICK", "REVERSAL", "SEISMICTOSS", "BULKUP", "DETECT", "QUICKGUARD", "VCREATE", "BLASTBURN", "ERUPTION", "OVERHEAT", "BLUEFLARE", "FIREBLAST", "FLAREBLITZ", "MAGMASTORM", "FUSIONFLARE", "HEATWAVE", "INFERNO", "SACREDFIRE", "SEARINGSHOT", "FLAMETHROWER", "BLAZEKICK", "FIERYDANCE", "LAVAPLUME", "FIREPUNCH", "FLAMEBURST", "FIREFANG", "FLAMEWHEEL", "FIREPLEDGE", "FLAMECHARGE", "EMBER", "FIRESPIN", "INCINERATE", "HEATCRASH", "SUNNYDAY", "WILLOWISP", "SKYATTACK", "BRAVEBIRD", "HURRICANE", "AEROBLAST", "FLY", "BOUNCE", "DRILLPECK", "AIRSLASH", "AERIALACE", "CHATTER", "PLUCK", "SKYDROP", "WINGATTACK", "ACROBATICS", "AIRCUTTER", "GUST", "PECK", "DEFOG", "FEATHERDANCE", "MIRRORMOVE", "ROOST", "TAILWIND", "SHADOWFORCE", "SHADOWBALL", "SHADOWCLAW", "OMINOUSWIND", "SHADOWPUNCH", "HEX", "SHADOWSNEAK", "ASTONISH", "LICK", "NIGHTSHADE", "CONFUSERAY", "CURSE", "DESTINYBOND", "GRUDGE", "NIGHTMARE", "SPITE", "FRENZYPLANT", "LEAFSTORM", "PETALDANCE", "POWERWHIP", "SEEDFLARE", "SOLARBEAM", "WOODHAMMER", "LEAFBLADE", "ENERGYBALL", "SEEDBOMB", "GIGADRAIN", "HORNLEECH", "LEAFTORNADO", "MAGICALLEAF", "NEEDLEARM", "RAZORLEAF", "GRASSPLEDGE", "MEGADRAIN", "VINEWHIP", "BULLETSEED", "ABSORB", "GRASSKNOT", "AROMATHERAPY", "COTTONGUARD", "COTTONSPORE", "GRASSWHISTLE", "INGRAIN", "LEECHSEED", "SLEEPPOWDER", "SPORE", "STUNSPORE", "SYNTHESIS", "WORRYSEED", "EARTHQUAKE", "EARTHPOWER", "DIG", "DRILLRUN", "BONECLUB", "MUDBOMB", "BULLDOZE", "MUDSHOT", "BONEMERANG", "SANDTOMB", "BONERUSH", "MUDSLAP", "FISSURE", "MAGNITUDE", "MUDSPORT", "SANDATTACK", "SPIKES", "FREEZESHOCK", "ICEBURN", "BLIZZARD", "ICEBEAM", "ICICLECRASH", "ICEPUNCH", "AURORABEAM", "GLACIATE", "ICEFANG", "AVALANCHE", "ICYWIND", "FROSTBREATH", "ICESHARD", "POWDERSNOW", "ICEBALL", "ICICLESPEAR", "SHEERCOLD", "HAIL", "HAZE", "MIST", "EXPLOSION", "SELFDESTRUCT", "GIGAIMPACT", "HYPERBEAM", "LASTRESORT", "DOUBLEEDGE", "HEADCHARGE", "MEGAKICK", "THRASH", "EGGBOMB", "JUDGMENT", "SKULLBASH", "HYPERVOICE", "ROCKCLIMB", "TAKEDOWN", "UPROAR", "BODYSLAM", "TECHNOBLAST", "EXTREMESPEED", "HYPERFANG", "MEGAPUNCH", "RAZORWIND", "SLAM", "STRENGTH", "TRIATTACK", "CRUSHCLAW", "RELICSONG", "CHIPAWAY", "DIZZYPUNCH", "FACADE", "HEADBUTT", "RETALIATE", "SECRETPOWER", "SLASH", "HORNATTACK", "STOMP", "COVET", "ROUND", "SMELLINGSALT", "SWIFT", "VICEGRIP", "CUT", "STRUGGLE", "TACKLE", "WEATHERBALL", "ECHOEDVOICE", "FAKEOUT", "FALSESWIPE", "PAYDAY", "POUND", "QUICKATTACK", "SCRATCH", "SNORE", "DOUBLEHIT", "FEINT", "TAILSLAP", "RAGE", "RAPIDSPIN", "SPIKECANNON", "COMETPUNCH", "FURYSWIPES", "BARRAGE", "BIND", "DOUBLESLAP", "FURYATTACK", "WRAP", "CONSTRICT", "BIDE", "CRUSHGRIP", "ENDEAVOR", "FLAIL", "FRUSTRATION", "GUILLOTINE", "HIDDENPOWER", "HORNDRILL", "NATURALGIFT", "PRESENT", "RETURN", "SONICBOOM", "SPITUP", "SUPERFANG", "TRUMPCARD", "WRINGOUT", "ACUPRESSURE", "AFTERYOU", "ASSIST", "ATTRACT", "BATONPASS", "BELLYDRUM", "BESTOW", "BLOCK", "CAMOUFLAGE", "CAPTIVATE", "CHARM", "CONVERSION", "CONVERSION2", "COPYCAT", "DEFENSECURL", "DISABLE", "DOUBLETEAM", "ENCORE", "ENDURE", "ENTRAINMENT", "FLASH", "FOCUSENERGY", "FOLLOWME", "FORESIGHT", "GLARE", "GROWL", "GROWTH", "HARDEN", "HEALBELL", "HELPINGHAND", "HOWL", "LEER", "LOCKON", "LOVELYKISS", "LUCKYCHANT", "MEFIRST", "MEANLOOK", "METRONOME", "MILKDRINK", "MIMIC", "MINDREADER", "MINIMIZE", "MOONLIGHT", "MORNINGSUN", "NATUREPOWER", "ODORSLEUTH", "PAINSPLIT", "PERISHSONG", "PROTECT", "PSYCHUP", "RECOVER", "RECYCLE", "REFLECTTYPE", "REFRESH", "ROAR", "SAFEGUARD", "SCARYFACE", "SCREECH", "SHARPEN", "SHELLSMASH", "SIMPLEBEAM", "SING", "SKETCH", "SLACKOFF", "SLEEPTALK", "SMOKESCREEN", "SOFTBOILED", "SPLASH", "STOCKPILE", "SUBSTITUTE", "SUPERSONIC", "SWAGGER", "SWALLOW", "SWEETKISS", "SWEETSCENT", "SWORDSDANCE", "TAILWHIP", "TEETERDANCE", "TICKLE", "TRANSFORM", "WHIRLWIND", "WISH", "WORKUP", "YAWN", "GUNKSHOT", "SLUDGEWAVE", "SLUDGEBOMB", "POISONJAB", "CROSSPOISON", "SLUDGE", "VENOSHOCK", "CLEARSMOG", "POISONFANG", "POISONTAIL", "ACID", "ACIDSPRAY", "SMOG", "POISONSTING", "ACIDARMOR", "COIL", "GASTROACID", "POISONGAS", "POISONPOWDER", "TOXIC", "TOXICSPIKES", "PSYCHOBOOST", "DREAMEATER", "FUTURESIGHT", "PSYSTRIKE", "PSYCHIC", "EXTRASENSORY", "PSYSHOCK", "ZENHEADBUTT", "LUSTERPURGE", "MISTBALL", "PSYCHOCUT", "SYNCHRONOISE", "PSYBEAM", "HEARTSTAMP", "CONFUSION", "MIRRORCOAT", "PSYWAVE", "STOREDPOWER", "AGILITY", "ALLYSWITCH", "AMNESIA", "BARRIER", "CALMMIND", "COSMICPOWER", "GRAVITY", "GUARDSPLIT", "GUARDSWAP", "HEALBLOCK", "HEALPULSE", "HEALINGWISH", "HEARTSWAP", "HYPNOSIS", "IMPRISON", "KINESIS", "LIGHTSCREEN", "LUNARDANCE", "MAGICCOAT", "MAGICROOM", "MEDITATE", "MIRACLEEYE", "POWERSPLIT", "POWERSWAP", "POWERTRICK", "PSYCHOSHIFT", "REFLECT", "REST", "ROLEPLAY", "SKILLSWAP", "TELEKINESIS", "TELEPORT", "TRICK", "TRICKROOM", "WONDERROOM", "HEADSMASH", "ROCKWRECKER", "STONEEDGE", "ROCKSLIDE", "POWERGEM", "ANCIENTPOWER", "ROCKTHROW", "ROCKTOMB", "SMACKDOWN", "ROLLOUT", "ROCKBLAST", "ROCKPOLISH", "SANDSTORM", "STEALTHROCK", "WIDEGUARD", "DOOMDESIRE", "IRONTAIL", "METEORMASH", "FLASHCANNON", "IRONHEAD", "STEELWING", "MIRRORSHOT", "MAGNETBOMB", "GEARGRIND", "METALCLAW", "BULLETPUNCH", "GYROBALL", "HEAVYSLAM", "METALBURST", "AUTOTOMIZE", "IRONDEFENSE", "METALSOUND", "SHIFTGEAR", "HYDROCANNON", "WATERSPOUT", "HYDROPUMP", "MUDDYWATER", "SURF", "AQUATAIL", "CRABHAMMER", "DIVE", "SCALD", "WATERFALL", "RAZORSHELL", "BRINE", "BUBBLEBEAM", "OCTAZOOKA", "WATERPULSE", "WATERPLEDGE", "AQUAJET", "WATERGUN", "CLAMP", "WHIRLPOOL", "BUBBLE", "AQUARING", "RAINDANCE", "SOAK", "WATERSPORT", "WITHDRAW", "CORALBREAK", "ATOMICPUNCH", "METALWHIP", "SHUFFLE", "NUCLEARWASTE", "GAMMARAY", "RADIOACID", "SKYFALL", "FLAMEIMPACT", "DRAININGKISS", "EERIEIMPULSE", "ELECTRICTERRAIN", "ELECTRIFY", "FAIRYLOCK", "FAIRYWIND", "FELLSTINGER", "FLOWERSHIELD", "FLYINGPRESS", "FORESTSCURSE", "FREEZEDRY", "GEOMANCY", "GRASSYTERRAIN", "INFESTATION", "IONDELUGE", "KINGSSHIELD", "LANDSWRATH", "MAGNETICFLUX", "MATBLOCK", "MISTYTERRAIN", "MOONBLAST", "MYSTICALFIRE", "NOBLEROAR", "NUZZLE", "OBLIVIONWING", "PARABOLICCHARGE", "PARTINGSHOT", "PETALBLIZZARD", "PHANTOMFORCE", "PLAYNICE", "PLAYROUGH", "POWDER", "POWERUPPUNCH", "ROTOTILLER", "SPIKYSHIELD", "STICKYWEB", "TOPSYTURVY", "TRICKORTREAT", "VENOMDRENCH", "WATERSHURIKEN", "AROMATICMIST", "BABYDOLLEYES", "BELCH", "BOOMBURST", "CONFIDE", "CRAFTYSHIELD", "DAZZLINGGLEAM", "DISARMINGVOICE", "SUBDUCTION", "INSTANTCRUSH", "GETLUCKY", "LASERPULSE", "GEMSTONEGLIMMER", "HALFLIFE", "OCEANSWRATH", "FISSIONBURST", "CAUSTICBREATH", "NUCLEARSLASH", "THUNDERSTORM", "SUDDENSTRIKE", "EXPUNGE", "FALLOUT", "PROTONBEAM", "INFERNALBLADE", "QUANTUMLEAP", "METALCRUNCHER", "DRAINLIFE", "STICKYTERRAIN"};
	//[E:tansheen(275088580093804544), E:hazmaBreeding(283450696970534912), E:Yoshi(242913559321772034), E:MetalheadHazma(256579922812862464), E:breeduntilyoubleed(269834497082523658), E:hopesanddreams(277388407204741121), E:Hazmacookie(283450124489719809), E:Spiderhazma(242295819867258880), E:Hazma(233355798720413696), E:HAZMA(253712746019946498), E:colonthree(283754243825860610), E:jetpackhazma(252466189438025728), E:lazy(269835087615492097), E:gentleCat(283871244544245761), E:Kawaii(242854765778698241), E:Chyinmunk(232930105821757442), E:SmugCat(241921432823660544), E:brothers(277322602614095872)]
	//[R:Tanscure(240312763140931594), R:Bantastic mod :v(221441314280046592), R:Harambe Hitman inc(221577035955306496), R:Mee6(223121610050240513), R:Tatsumaki(223173049233571840), R:NeoUraniumBot(309533393656348673), R:kernels(272946299748483072), R:Server owner dream(228475589554733056), R:The Original Chat lurker(221356078082818049), R:Mad Hax(223795655414513664), R:Mild colored Potato(221343690101096449), R:Breedzards(222327235380510720), R:Moderator(221332112085745670), R:¯\_(?)_/¯(225940269650935809), R:Forum Admin(224828408209604608), R:Rare Male Selkid(237588051021266944), R:Kawaii Snek(239013237171945473), R:Make your dreams come true(248231878912507905), R:Developer(224191129682182144), R:WT extravaganza organizer(231698605034045440), R:Master Breeder(250354215774322690), R:Bred the dex(237601592730320897), R:Bots(223121776534749186), R:Insane genderless breeder(239776301869826048), R:Hidden power Breeder(233491020635963393), R:Shiny breeder(231699548819423232), R:Egg move Breeder(221584492190629890), R:Troll Breeder(246510241418706945), R:Veteran Breeder(223122234552745986), R:Breeder(225266317593149440), R:Still Hatching(223122640326492161), R:FRN(250357408268943361), R:Event organizer(277222764861980673), R:Fluffy Wofie Lycanroc of Doom(290456752338763786), R:Tancoon Bot(299551008349093891), R:@everyone(221317397653487626)]
	
	 private final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
	 private final Map<Long, GuildMusicManager> musicManagers = new HashMap<>();

	  
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e) {
		//221317397653487626 breeder guild id
		if(e.getGuild().getId().equals("221317397653487626")){
			String user = e.getMember().getAsMention();
			e.getGuild().getTextChannelById(e.getGuild().getId()).sendMessage("Welcome "+user+"!! Do you love Tancoons? Oh and my friend Neo wants me to tell you to read <#224397009073733632>. It's there for a reason!").queue();
		}
		
	}
	
	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent e) {

		String user = e.getMember().getAsMention();
		//221317397653487626 breeder guild general id
		if(e.getGuild().getId().equals("221317397653487626")){
			e.getGuild().getTextChannelById("221317397653487626").getHistory().retrievePast(10).queue((List<Message> messages) -> {
				
				for(Message m:messages) {
					//mee6 bot id
					if(m.getAuthor().getId().equals("159985870458322944")){
						if(m.getContentRaw().startsWith(user)){
							MessageBuilder messageb = new MessageBuilder().append(" ");
							
							File f = new File("img/nope.gif");
							Message message = messageb.build();
							e.getGuild().getTextChannelById("221317397653487626").sendFile(f, "nope.gif", message).queue();
						}

					}
				}

				
			});
		}
		
	}
	
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		super.onMessageReceived(e);
		try {
			if(!e.getAuthor().isBot()){
				
				//egg hatching game
				String userID_game = e.getAuthor().getId();
				
				JsonReader reader_game = new JsonReader(new FileReader("JSON/game.json"));
				Gson gson_game = new Gson();
				ArrayList<Game_user> users_game = new ArrayList<Game_user>() ;
				
				users_game = gson_game.fromJson(reader_game, new TypeToken<List<Game_user>>(){}.getType());
				
				if(checkUser(users_game, userID_game)){
					
					for(Game_user u:users_game){
						if(u.getUserID().equals(userID_game)){
							u.setSteps((int) (u.getSteps()+Math.random()*STEPCOUNT+1));
							if(u.getSteps()>=u.getEgg().getSteps()) {
								if(e.getGuild().getId().equals("221317397653487626")){
									e.getGuild().getTextChannelById("223126199784701952").sendMessage("Huh?").queue();
									
									if(u.getEgg().getShiny()){
										e.getChannel().sendMessage(e.getAuthor().getName() + ", " + u.getEgg().getPokemon() + "(Shiny) just hatched from the egg!").queueAfter(3, TimeUnit.SECONDS);
										u.getCollection().add(u.getEgg().getPokemon()+"(Shiny)");
									}
									else {
										e.getChannel().sendMessage(e.getAuthor().getName() + ", " + u.getEgg().getPokemon() + " just hatched from the egg!").queueAfter(3, TimeUnit.SECONDS);
										u.getCollection().add(u.getEgg().getPokemon());
									}
									u.setSteps(0);
									u.setEgg(generateEgg());
								}
								else {
									e.getChannel().sendMessage("Huh?").queue();
									
									if(u.getEgg().getShiny()){
										e.getChannel().sendMessage(e.getAuthor().getName() + ", " + u.getEgg().getPokemon() + "(Shiny) just hatched from the egg!").queueAfter(3, TimeUnit.SECONDS);
										u.getCollection().add(u.getEgg().getPokemon()+"(Shiny)");
									}
									else {
										e.getChannel().sendMessage(e.getAuthor().getName() + ", " + u.getEgg().getPokemon() + " just hatched from the egg!").queueAfter(3, TimeUnit.SECONDS);
										u.getCollection().add(u.getEgg().getPokemon());
									}
									u.setSteps(0);
									u.setEgg(generateEgg());
								}
								
								
							}
						}
					}
					
					try (Writer writer_game = new FileWriter("JSON//game.json")) {
					    Gson wgson_game = new GsonBuilder().create();
					    wgson_game.toJson(users_game, writer_game);
					  
					}
				}
				
				//272986135372759040 Custom sprite channel ID
				//267561135245361153 bot-test Picture channel ID
				if(e.getChannel().getId().equals("272986135372759040")/*||e.getChannel().getId().equals("267561135245361153")*/) {
					if(!e.getMessage().getContentRaw().split(" ")[0].equals("!clear")) {
						if(e.getMessage().getAttachments().isEmpty()) {
							if(!e.getMessage().getContentRaw().startsWith(">>")){
								//175984908802457600 User Vyhn id
								e.getGuild().getMemberById("175984908802457600").getUser().openPrivateChannel().queue((value) ->
							    {
							        PrivateChannel channel = value; 
							        channel.sendMessage("Someone posted in custom sprite channel\n"+
							        						"**User: **"+e.getAuthor().getName()+"\n"+
							        						"**Message: **"+e.getMessage().getContentRaw()+"\n"		
							        						).queue();
			
							    });
								e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
								e.getChannel().sendMessage("This is a channel for custom sprite sharing only. Please do not chat here. Only image can be posted.\nIf you want to add message to your file, you can either add it as a comment or type '>>' before your message.").queue(message -> message.delete().queueAfter(20, TimeUnit.SECONDS));
							}
						}
					}
				}
				
				String[] message = e.getMessage().getContentRaw().trim().split(" +");
				
				
				
				/* get webhook url from breeder guild (no permission)
				if(message[0].equals("v!getwebhook")&&e.getAuthor().getId().equals("175984908802457600")){
					e.getJDA().getGuildById("").getWebhooks();
					for(Webhook w: e.getGuild().getWebhooks().complete()) {
						System.out.println(w.getName()+": "+w.getUrl());
					}
				}*/
					
				boolean checkblock = false;
				JsonReader checkreader = new JsonReader(new FileReader("JSON/tancoonBlock.json"));
				Gson checkgson = new Gson();
				ArrayList<String> blockList = new ArrayList<String>() ;
				
				blockList = checkgson.fromJson(checkreader, new TypeToken<List<String>>(){}.getType());
				
				for(String id:blockList){
					if(id.equals(e.getAuthor().getId())){
						checkblock = true;
					}
				}
				
				if(!e.getChannelType().equals(ChannelType.PRIVATE)) {
					if(!checkblock) {
						if(e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains("breeduntilyoubleed")/*&&!message[0].startsWith("v!")&&!message[0].startsWith(">")*/) {
							//249187330731147264 Vyhn bot id
							if(!e.getAuthor().getId().equals("249187330731147264")) {
								//221317397653487626 PU breeder guild id
								if(e.getGuild().getId().equals("221317397653487626")){
									//269834497082523658 breeduntillyoubleed emote id
									e.getMessage().addReaction(e.getGuild().getEmoteById("269834497082523658")).queue();
								}
								
							}
						}
						/*if(e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains("yousawnothing")&&!message[0].startsWith("v!")&&!message[0].startsWith(">")) {
							//249187330731147264 Vyhn bot id
							if(!e.getAuthor().getId().equals("249187330731147264")) {
								//221317397653487626 PU breeder guild id
								if(e.getGuild().getId().equals("221317397653487626")){
									MessageBuilder messageb = new MessageBuilder().append(" ");
									
									File f = new File("img\\yousawnothing.png");
									Message m = messageb.build();
									System.out.println(messageb.build().getContent());
									System.out.println(f);
									try {
										e.getChannel().sendFile(f, "yousawnothing.png", m).queue();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								
							}
						}*/
						/*o7*/
						if(e.getMessage().getContentRaw().equals("o7")) {
							e.getChannel().sendMessage("https://lh3.googleusercontent.com/-TumSoV9PYT8/VtIkwlpRN4I/AAAAAAAACq4/kgQ1dV_WkUo/w506-h750/200.gif").queue();
						}
						/*good nature question*/
						if((e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains("goodnature")||e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains("bestnature"))&&e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains("what")&&!message[0].startsWith("v!")&&!message[0].startsWith(">")) {
							//249187330731147264 Vyhn bot id
							if(!e.getAuthor().getId().equals("249187330731147264")) {
								//221317397653487626 PU breeder guild id
								if(e.getGuild().getId().equals("221317397653487626")){
									e.getChannel().sendMessage("It depends on what you wanna it to do.").queue();
								}
								
							}
						}
						/*hopes and dreams*/
						if(e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains("hopesanddreams")&&!message[0].startsWith("v!")&&!message[0].startsWith(">")) {
							//249187330731147264 Vyhn bot id
							if(!e.getAuthor().getId().equals("249187330731147264")) {
								//221317397653487626 PU breeder guild id
								if(e.getGuild().getId().equals("221317397653487626")){
									//277388407204741121 hopesanddreams/gargryph emote id
									e.getMessage().addReaction(e.getGuild().getEmoteById("277388407204741121")).queue();
								}
								
							}
						}
						/*TANCOON!*/
						for (String s : Tancoons)
						{
						  if (((e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase().contains(s)||(new StringBuilder(e.getMessage().getContentRaw().replaceAll("\\-|\\s+","").toLowerCase()).reverse().toString().contains(s))))&&!message[0].startsWith("v!")&&!message[0].startsWith(">"))
						  {
							//249187330731147264 Vyhn bot id
							if(!e.getAuthor().getId().equals("249187330731147264")) {
								
								String userID = e.getAuthor().getId();
								
								JsonReader reader_bot = new JsonReader(new FileReader("JSON/botCount.json"));
								Gson gson_bot = new Gson();
								Map<String, String> botCount = gson_bot.fromJson(reader_bot, new TypeToken<Map<String, String>>(){}.getType());
								
								String count = botCount.get(userID);
								
								if(count!=null){
									count = Integer.toString((Integer.parseInt(count)+1));
									botCount.put(userID, count);
								}
								else {
									botCount.put(userID, "1");
								}
								
								try (Writer writer_bot = new FileWriter("JSON//botCount.json")) {
								    Gson wgson_bot = new GsonBuilder().create();
								    wgson_bot.toJson(botCount, writer_bot);
								  
								}
								
								//achievements
								if(count!=null){
								String achievement = null;
								String avalue = null;
									switch(count){
									case "10": 
										achievement = "TANCOON!";
										avalue = "Triggered Tancoon-bot 10 times.";
										break;
									case "50": 
										achievement = "I Love Tancoon";
										avalue = "Triggered Tancoon-bot 50 times.";
										break;
									case "100": 
										achievement = "Tancoon Lover";
										avalue = "Triggered Tancoon-bot 100 times.";
										break;
									case "150": 
										achievement = "Tancoon Maniac";
										avalue = "Triggered Tancoon-bot 150 times.";
										break;
									case "200": 
										achievement = "Aiden";
										avalue = "Triggered Tancoon-bot 200 times.";
										break;
										
									}
									if(achievement!=null){
										final String achievement_final = achievement;
										final String avalue_final = avalue;
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									    	PrivateChannel channel = value; 
									    	EmbedBuilder embed = new EmbedBuilder();
											embed.setTitle("Achievement unlocked!!")
												 .setDescription("\u200B")
												 .addField(achievement_final,avalue_final, true)
												 .setColor(new Color(194, 124, 14))
												 .setThumbnail("https://cdn.discordapp.com/avatars/249187330731147264/0c27b4aceac24fa1da13cf8ae5c7947e.jpg");

											channel.sendMessage(embed.build()).queue();
									    });
									}
								}
								
								
								/*
								e.getAuthor().openPrivateChannel().queue((value) ->
							    {
							    	
							        PrivateChannel channel = value; 
							        
							        if(s.equals("tanscure")){
							        	channel.sendMessage("TANSCURE!!!").queue();
							        }
							        else if(s.equals("tansheen")){
							        	channel.sendMessage("TANSHEEN!!!").queue();
							        }
							        else{
							        	channel.sendMessage("TANCOON!!!").queue();
							        }
			
							    });*/
								
								//221317397653487626 PU breeder guild id
								//275088580093804544 tansheen emote id
								e.getMessage().addReaction(e.getJDA().getGuildById("221317397653487626").getEmoteById("275088580093804544")).queue();
							}
						    break;
						  }
						}
					}
		
					
					if(e.getMessage().getContentRaw().toLowerCase().contains("vyhn")){
						
						if(!e.getAuthor().isBot()) {
							//175984908802457600 User Vyhn id
							e.getGuild().getMemberById("175984908802457600").getUser().openPrivateChannel().queue((value) ->
						    {
						        PrivateChannel channel = value; 
						        channel.sendMessage("Someone mention you...\n"+
										        		"**Guild: **"+e.getGuild().getName()+"\n"+
							    						"**Channel: **"+e.getChannel().getName()+"\n"+
						        						"**User: **"+e.getAuthor().getName()+"\n"+
						        						"**Message: **"+e.getMessage().getContentRaw()+"\n"		
						        						).queue();
		
						    });
						}
						
		
					}
		
					//Potato id
					if(e.getAuthor().getId().equals("221333848661688321")) {
						if(e.getMessage().getContentRaw().toLowerCase().equals("lurk")){
							//Hazma emote id
							e.getMessage().addReaction(e.getGuild().getEmoteById("233355798720413696")).queue();
						}
					}
					/*
					//Ç to J
					
					if(e.getGuild().getId().equals("221317397653487626")){
						if(e.getGuild().getMemberById("261621136209608704").getOnlineStatus().equals(OnlineStatus.OFFLINE)){
							if(e.getAuthor().getId().equals("279084770191998977")||e.getAuthor().getId().equals("175984908802457600")){
								String[] Jmessage = e.getMessage().getContentRaw().split(" ");
								String text = "";
								for(String s: Jmessage){
									if(s.contains("Ç")||s.contains("ç")) {
										text += "*" + s.replace("Ç", "J").replace("ç", "j") + "\n";
									}
								}
								if(text!=""){
									e.getChannel().sendMessage(text).queue();
								}	
							}
						}
					}

					
					*/
					
					//J to Ç
					/*
					if(e.getAuthor().getId().equals("279084770191998977")){
						String[] Jmessage = e.getMessage().getContentRaw().split(" ");
						String text = "";
						for(String s: Jmessage){
							if(s.contains("J")||s.contains("j")) {
								text += "*" + s.replace("J", "Ç").replace("j", "ç") + "\n";
							}
						}
						if(text!=""){
							e.getChannel().sendMessage(text).queue();
						}
						
							
					}
					*/
					//bot mention
					//249187330731147264 vyhn bot id
					if(e.getMessage().getContentRaw().equals(e.getGuild().getMemberById("249187330731147264").getAsMention())){
						System.out.println(e.getAuthor().getAsMention()+e.getGuild().getMemberById("249187330731147264").getAsMention());
						e.getChannel().sendMessage(e.getAuthor().getAsMention()).queue();
					}
					
					//check command start
					if(message[0].startsWith("v!")){
						//breeders guild only command
						if(e.getGuild().getId().equals("221317397653487626")) {
							//command get role
							
							if(message[0].equalsIgnoreCase("v!getrole")) {
								if(e.getGuild().getId().equals("221317397653487626")) {
									if(e.getMember().getRoles().isEmpty()){
										e.getGuild().getController().addSingleRoleToMember(e.getMember(), e.getGuild().getRoleById("223122640326492161")).reason("User Request").queue();
									}
									else {
										e.getChannel().sendMessage("Sorry, only member without a role can claim the role.").queue();
									}
								}
								
							}
							
							//command portfolio 
							else if(message[0].equalsIgnoreCase("v!port")) {
								if(e.getGuild().getId().equals("221317397653487626")){
									if(message.length>1){
										
										String userid = "";
										if(message[1].startsWith("<@")){
											userid = message[1].substring(2, 20);
										}
										
										else
										{
											for(int i=1;i<message.length;i++) {
												userid += message[i];
											}
											
										}
										final String user = userid;
										System.out.println(user);
										//221317397653487626 PU breeder guild id
										if(e.getGuild().getId().equals("221317397653487626")){
											//221351400259584001 portfolio channel id
											e.getGuild().getTextChannelById("221351400259584001").getHistory().retrievePast(100).queue((List<Message> messages) -> {
												
												boolean portcheck = false;
												String port = "";
												String bestUser = "";
												double similarity = 0;
												for(Message m:messages) {
													
													if(similarity(user,m.getAuthor().getName())>similarity){
														similarity = similarity(user,m.getAuthor().getName());
														bestUser = m.getAuthor().getName();
													}
													System.out.println("User: "+m.getAuthor().getName()+" "+similarity(user,m.getAuthor().getName()));
													System.out.println("User: "+bestUser+" "+similarity);
												}
												System.out.println("BestUser: "+bestUser+" "+"similarity: "+similarity);
												for(Message m:messages) {
													if(similarity>=0.6&&m.getAuthor().getName().equalsIgnoreCase(bestUser)){
														port = port + m.getContentRaw() +"\n";
														portcheck = true;
													}
												}
												if(!portcheck){
													e.getChannel().sendMessage("Sorry, user not found.").queue();
												}
												else {
													e.getChannel().sendMessage(port).queue();
												}
												
											});
											
										}
										
									}
									else {
										e.getChannel().sendMessage("Sorry, input invalid.").queue();
									}
								}
								
								
							}
						}
						if(message[0].equalsIgnoreCase("v!ping")){
							e.getChannel().sendMessage("pong").queue();
						}
						
						else if(message[0].equalsIgnoreCase("v!test")&&e.getMember().getUser().getId().equals("175984908802457600")) {
							/*
							EmbedBuilder embed = new EmbedBuilder();
							embed.setTitle("Achievement unlocked!!")
								 .setDescription("\u200B")
								 .addField("TANCOON!","Triggered Tancoon-bot 10 times.", true)
								 .setColor(new Color(194, 124, 14))
								 .setThumbnail("https://cdn.discordapp.com/avatars/249187330731147264/0c27b4aceac24fa1da13cf8ae5c7947e.jpg");

							e.getChannel().sendMessage(embed.build()).queue();
							*/
							System.out.println(e.getJDA().getGuildById("221317397653487626").getRoles());
						}
						
						//command yousawnothing
						else if(message[0].equalsIgnoreCase("v!yousawnothing")) {
							MessageBuilder messageb = new MessageBuilder().append(" ");
							
							File f = new File("img/yousawnothing.png");
							Message m = messageb.build();
							System.out.println(messageb.build().getContentDisplay());
							System.out.println(f);
							e.getChannel().sendFile(f, "yousawnothing.png", m).queue();
						
							
						}
						
						//command anarchy
						else if(message[0].equalsIgnoreCase("v!anarchy")) {
							MessageBuilder messageb = new MessageBuilder().append(" ");
							
							File f = new File("img/anarchy.PNG");
							Message m = messageb.build();
							System.out.println(messageb.build().getContentDisplay());
							System.out.println(f);
							e.getChannel().sendFile(f, "anarchy.png", m).queue();
						
							
						}
						
						else if(message[0].equalsIgnoreCase("v!tancoon")||message[0].equalsIgnoreCase("v!tanscure")||message[0].equalsIgnoreCase("v!tansheen")) {
							System.out.print("tancoon");
							MessageBuilder messageb = new MessageBuilder().append(" ");
							
							File f = new File("img/tancoon_"+(int )(Math.random() * 6 + 1)+".png");
							Message m = messageb.build();
							e.getChannel().sendFile(f, "Tancoon.png", m).queue();
						}
						
						//change bot status
						else if(message[0].equalsIgnoreCase("v!bot")){
							
							//221332112085745670 moderator role id
							//175984908802457600 user Vyhn id
							if(e.getMember().getRoles().contains(e.getGuild().getRoleById("221332112085745670"))||e.getAuthor().getId().equals("175984908802457600")){
								if(message.length>1){
									if(message[1].equalsIgnoreCase("game")) {
										String game = "";
										for(int i=2;i<message.length;i++){
											if(game.equals("")){
												game = game + message[i];
											}
											else {
												game = game + " " + message[i];
											}
											 
										}
										
										e.getJDA().getPresence().setGame(Game.of(Game.GameType.DEFAULT, game));
										System.out.println(e.getJDA().getPresence().getGame());
									}
									else if(message[1].equalsIgnoreCase("status")){
										
										OnlineStatus status = e.getJDA().getPresence().getStatus();
										
										switch(message[2]) {
					                        case "online":
					                            status = OnlineStatus.ONLINE;
					                            break;
					                        case "idle":
					                            status = OnlineStatus.IDLE;
					                            break;
					                        case "dnd":
					                            status = OnlineStatus.DO_NOT_DISTURB;
					                            break;
					                        case "invisible":
					                            status = OnlineStatus.INVISIBLE;
					                            break;
					                        default:
					                            status = OnlineStatus.ONLINE;
					                            break;
										}
										
										e.getJDA().getPresence().setStatus(status);
										System.out.println(e.getJDA().getPresence().getStatus());
									}
									
								}
							}
							else {
								e.getChannel().sendMessage("Sorry you don't have permission to perform this command.").queue();
							}
							
						}
						
						//command help
						else if(message[0].equalsIgnoreCase("v!help")) {
							
							String help = "```\n"+"<> - required\n() - optional\n\n"
									+"v!help - well you are here already...you know what this is\n\n"
									+"v!ping - check to see if the bot is dead\n\n"
									+"v!hp <hp> <atk> <def> <sp.atk> <sp.def> <speed>  - calculate hidden power\n\n"
									+"v!nature - check the nature table\n\n"
									+"v!eggspawn - check the egg spawn rate\n\n"
									+"v!poke <pokemon>/<dex no.> ('abilities'/'stats'/'gender'/'egggroups'/'eggmoves'/'tms'/'moves') - check the information of that pokemon\n\n"
									+"v!block block/unblock - block/unblock tancoon-bot from responding to your message\n\n"
									+"v!eggmove <pokemon>/<dex no.> <eggmove> - search for possible breeding chain for eggmoves (only 1st chain is available for now)\n\n"
									+"v!music <play> <url>/<skip>/<stop>/<volume> <1-50> - listen to music\n\n";
									
							//221332112085745670 moderator role id
							//175984908802457600 User Vyhn id
							if(e.getGuild().getId().equals("221317397653487626")) {
								help = help +"v!port <username>/<user tag> - check the portfolio of that user\n\n"
										+ "v!getrole - claim 'still hatching' role if you do not have any role\n\n";
							}
							if(e.getMember().getRoles().contains(e.getGuild().getRoleById("221332112085745670"))||e.getAuthor().getId().equals("175984908802457600")){
								help = help + "v!bot <'game'>/<'status'> <game name>/<status> - change the bot's game/status, status include ('online'), ('idle'), ('dnd') and ('invisible')\n";
								help = help + "```"+"\n";
								e.getChannel().sendMessage(help).queue();
							}
							else {
								help = help + "```"+"\n";
								e.getChannel().sendMessage(help).queue();
							}
								
						}
						
						else if(message[0].equalsIgnoreCase("v!timer")) {
							if(message.length>1){
								if(isStringInt(message[1]));
								int timer = Integer.parseInt(message[1]);
								e.getChannel().sendMessage("**Timer: **"+message[1]+"s\nTimer starts now."  ).queue();
								e.getChannel().sendMessage("**Time is up!**").queueAfter(timer, TimeUnit.SECONDS);
							}
						}
						
						
						//command music
						else if(message[0].equalsIgnoreCase("v!music")) {
							if(message[1].equals("skip")&&message.length==2){
								skipTrack(e.getTextChannel());
							}
							else if(message[1].equals("stop")&&message.length==2){
								if(e.getGuild().getAudioManager().isConnected()){
									e.getGuild().getAudioManager().closeAudioConnection();
								}
								
							}
							else if(message[1].equals("join")&&message.length==2){
								if(!e.getGuild().getAudioManager().isConnected()){
									AudioManager audioManager = e.getGuild().getAudioManager();
									audioManager.openAudioConnection(e.getGuild().getVoiceChannels().get(0));
								}
								
							}
							else if(message[1].equals("play")&&message.length==3){
								AudioManager audioManager = e.getGuild().getAudioManager();
								
								//audioManager.openAudioConnection(e.getGuild().getVoiceChannelById("249187735011590145"));
								
								AudioSourceManagers.registerRemoteSources(playerManager);
								AudioSourceManagers.registerLocalSource(playerManager);
								
								AudioPlayer player = playerManager.createPlayer();
								player.setVolume(1);
								System.out.println(player.getVolume());
								audioManager.setSendingHandler(new AudioPlayerSendHandler(player));
								
								
								TrackScheduler trackScheduler = new TrackScheduler(player);
								player.addListener(trackScheduler);
								
								loadAndPlay(e.getTextChannel(), message[2]);
									
								e.getJDA().getPresence().setGame(Game.of(Game.GameType.DEFAULT, "Pokepod"));
								
							}
							else if(message[1].equals("volume")&&message.length==3){
								if(isStringInt(message[2])&&Integer.parseInt(message[2])<=50&&Integer.parseInt(message[2])>=1) {
									changeVolume(Integer.parseInt(message[2]),e.getTextChannel());
								}
							}
							else if(message[1].equals("random")&&message.length==2) {
								AudioManager audioManager = e.getGuild().getAudioManager();
								
								//audioManager.openAudioConnection(e.getGuild().getVoiceChannelById("249187735011590145"));
								
								AudioSourceManagers.registerRemoteSources(playerManager);
								AudioSourceManagers.registerLocalSource(playerManager);
								
								AudioPlayer player = playerManager.createPlayer();
								audioManager.setSendingHandler(new AudioPlayerSendHandler(player));
								
								TrackScheduler trackScheduler = new TrackScheduler(player);
								player.addListener(trackScheduler);
								
								final File dir = new File("music");
								
								File[] files = dir.listFiles();

								Random rand = new Random();

								String track = files[rand.nextInt(files.length)].getAbsolutePath();
								
								System.out.println(track);
								
								loadAndPlay(e.getTextChannel(), track);
							}
							else if(message[1].equals("pause")&&message.length==2){
								GuildMusicManager musicManager = getGuildAudioPlayer(e.getGuild());
								if(musicManager.player.isPaused()){
									e.getChannel().sendMessage("Player is paused already.").queue();
								}
								else {
									pause(e.getTextChannel());
								}
								
							}
							else if(message[1].equals("unpause")&&message.length==2){
								GuildMusicManager musicManager = getGuildAudioPlayer(e.getGuild());
								if(!musicManager.player.isPaused()){
									e.getChannel().sendMessage("Player is not paused.").queue();
								}
								else {
									pause(e.getTextChannel());
								}
								
							}
						}
						
						
						
						//command poke
						else if(message[0].equalsIgnoreCase("v!poke")) {
							try {
								if(message.length>1){
									boolean neoStatus = false;
									if(e.getGuild().getId().equals("221317397653487626")){
										if(e.getGuild().getMemberById("261621136209608704").getOnlineStatus().equals(OnlineStatus.ONLINE)){
											neoStatus = true;
										}
									}
									
									e.getChannel().sendMessage(getPokeStat(message,neoStatus)).queue();
								}
								else {
									e.getChannel().sendMessage("You missed the Pokemon name...Do you want me to spam all pokemon?").queue();
								}
								
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						//command hidden power
						else if(message[0].equalsIgnoreCase("v!hp")) {
							if(message.length==1){
								MessageBuilder messageb = new MessageBuilder().append(" ");
								
								File f = new File("img/hp.png");
								Message m = messageb.build();
								System.out.println(messageb.build().getContentDisplay());
								System.out.println(f);
								e.getChannel().sendFile(f, "hp.png", m).queue();
							}
							else if(message.length==2) {
								String type = message[1].toLowerCase();
								
								String hpstat = "";
								switch(type) {
									case "fighting":
										hpstat = "X/X/E/E/E/E";
										break;
									case "flying":
										hpstat = "X/X/O/E/E/E";
										break;
									case "rock":
										hpstat = "X/X/E/O/E/E";
										break;
									case "bug":
										hpstat = "X/X/O/O/E/E";
										break;
									case "fire":
										hpstat = "X/X/E/E/O/E";
										break;
									case "water":
										hpstat = "X/X/O/E/O/E";
										break;
									case "psychic":
										hpstat = "X/X/E/O/O/E";
										break;
									case "ice":
										hpstat = "X/X/O/O/O/E";
										break;
									case "poison":
										hpstat = "X/X/E/E/E/O";
										break;
									case "ground":
										hpstat = "X/X/O/E/E/O";
										break;
									case "ghost":
										hpstat = "X/X/E/O/E/O";
										break;
									case "steel":
										hpstat = "X/X/O/O/E/O";
										break;
									case "grass":
										hpstat = "X/X/E/E/O/O";
										break;
									case "electric":
										hpstat = "X/X/O/E/O/O";
										break;
									case "dragon":
										hpstat = "X/X/E/O/O/O";
										break;
									case "dark":
										hpstat = "X/X/O/O/O/O";
										break;
									case "fairy":
										hpstat = "O/O/O/O/O/O";
										break;
									default:
										hpstat = "Invalid input.";
										break;
								}
								
								e.getChannel().sendMessage(hpstat).queue();
									
							}
							else if(message.length==7) {
								
								boolean statcheck = true;
								for(int i=1;i<message.length;i++){
									if(isStringInt(message[i])){
										if(Integer.parseInt(message[i])<0||Integer.parseInt(message[i])>31) {
											statcheck = false;
										}
									}
									else {
										statcheck = false;
									}
									
								}
								if(statcheck==true){
									e.getChannel().sendMessage(getHp(message)).queue();
								}
								else {
									e.getChannel().sendMessage("Input invalid. Please try again").queue();
								}
								
							}
							else
								e.getChannel().sendMessage("Input invalid. Please try again").queue();
							
						}
						
						
						//command nature
						else if(message[0].equalsIgnoreCase("v!nature")) {
							MessageBuilder messageb = new MessageBuilder().append(" ");
							
							File f = new File("img/nature.png");
							Message m = messageb.build();
							e.getChannel().sendFile(f, "nature.png", m).queue();
						
							
						}
						
						//command egg spawn
						else if(message[0].equalsIgnoreCase("v!eggspawn")) {
							MessageBuilder messageb = new MessageBuilder().append("Every 256 steps the game rolls a number to check it it should spawn an egg. The chances of this can be determined by what the daycare man says.");
							
							File f = new File("img/eggspawn.png");
							Message m = messageb.build();
							e.getChannel().sendFile(f, "eggspawn.png", m).queue();
						
							
						}
						//command get id
						else if(message[0].equalsIgnoreCase("v!id")){
							if(e.getAuthor().getId().equals("175984908802457600")) {
								for(Role r:e.getGuild().getRolesByName(message[1],false)){
									System.out.println(r.getName()+": "+r.getId());
								}
							}
						}
						//command egg moves
						else if(message[0].equalsIgnoreCase("v!eggmove")) {
							if(message.length==2){
								String poke = message[1];
								if(poke.matches("^-?\\d+$")){
									poke = convertpokenum(poke);
								}
								
								if(!checkpoke(poke)) {
									String t = "Sorry, pokemon not found. Are you sure it is spelled correctly?\n";
									if(!guessName(poke.substring(0, 1)).equals("")){
										t = t + "Are you searching for these pokemon?\n" + guessName(poke.substring(0, 1));
									}
									e.getChannel().sendMessage(t).queue();
								}
								else {
									e.getChannel().sendMessage("To check the eggmove chains, please enter both pokemon and eggmove.\n\n**"+poke+"**\n\n**Eggmoves:\n**"+convertpokestring(poke).getEggmoves()).queue();
								}
							}
							else if(message.length>2){
								String poke = message[1];
								String eggmove = "";
								for(int i=2;i<message.length;i++){
									eggmove = eggmove+message[i]+" ";
								}
								
								if(poke.matches("^-?\\d+$")){
									poke = convertpokenum(poke);
								}
								
								if(!checkpoke(poke)) {
									String t = "Sorry, pokemon not found. Are you sure it is spelled correctly?\n";
									if(!guessName(poke.substring(0, 1)).equals("")){
										t = t + "Are you searching for these pokemon?\n" + guessName(poke.substring(0, 1));
									}
									e.getChannel().sendMessage(t).queue();
								}
								else if(convertpokestring(poke).getEgggroup().toLowerCase().contains("ditto")||convertpokestring(poke).getEgggroup().toLowerCase().contains("undiscovered")) {
									e.getChannel().sendMessage("Pokemon with "+convertpokestring(poke).getEgggroup()+" egg group cannot be bred.").queue();
									
								}
								else if(convertpokestring(poke).getGender().equals("0")) {
									e.getChannel().sendMessage("Genderless pokemon cannot get eggmoves.").queue();
								}
								else if(!checkmove(eggmove)) {
									e.getChannel().sendMessage(eggmove+"is not a move.\n**Eggmoves:\n**"+convertpokestring(poke).getEggmoves()).queue();
								}
								else if(!checkeggmove(poke, eggmove)){
									String t = poke + " does not have " + eggmove + "as an eggmove.\n**Eggmoves:\n**"+convertpokestring(poke).getEggmoves();
									e.getChannel().sendMessage(t).queue();
								}
								else {
									Map<String, ArrayList<String>> chains = searcheggmove(convertpokestring(poke), eggmove, new HashMap<String, ArrayList<String>>());
									System.out.println("Chain: "+searcheggmove(convertpokestring(poke), eggmove, new HashMap<String, ArrayList<String>>()));
									
									
									ArrayList<String> lvlmove = new ArrayList<String>();
									ArrayList<String> tm = new ArrayList<String>();
									ArrayList<String> eggmoves = new ArrayList<String>();
									ArrayList<String> chainlist = new ArrayList<String>();
									//Level-up Moves
									
									for (Map.Entry<String, ArrayList<String>> pair : chains.entrySet()) {
										String key = pair.getKey();
										ArrayList<String> list = pair.getValue();
										for(String value: list) {
								        	  if(key.equalsIgnoreCase(poke+"(move)")){
								        		  lvlmove.add(value.toString());
								        		  System.out.println(key+":"+poke+"(move)");
								        		  System.out.println("Add move");
								        	  }
								        	  else if(key.equalsIgnoreCase(poke+"(tm)")){
								        		  tm.add(value.toString());
								        		  System.out.println(key+":"+poke+"(tm)");
								        		  System.out.println("Add tm");
								        	  }
								        	  else if(key.equalsIgnoreCase(poke+"(eggmove)")){
								        		  eggmoves.add(value.toString());
								        		  System.out.println(key+":"+poke+"(eggmove)");
								        		  System.out.println("Add eggmove");
								        	  }
								        	  /*if(key.endsWith("(eggmove)")){				        		  
								        		  String next = value.toString();
								        		  String newchain = poke;
								        		  if(!next.equalsIgnoreCase(poke)){
								        			  System.out.println("newchain: "+newchain+" next: "+next);
								        			  newchain = generatechainlist(newchain, next, chains);
								        		  }
								        		  else {
								        			  newchain = "";
								        		  }
								        		  
								        		  if(!newchain.equals("")){
								        			  chainlist.add(newchain);
								        		  }
								        		  
								        		  
								        	  }*/
								          
								       }
								    }
									
									
									
									
									String moves = "";
									String TM = "";
									String eggmovess = "";
									String chaineggmove = "";
									String result = "";
									
									for(String s:lvlmove){
										if(moves.equals("")){
						        			  moves = s;
						        		  }
						        		  else {
						        			  moves += ", " + s;
						        		  }
									}
									
									for(String s:tm){
										if(TM.equals("")){
											TM = s;
						        		  }
						        		  else {
						        			  TM += ", " + s;
						        		  }
									}
									
									for(String s:eggmoves){
										if(eggmovess.equals("")){
											eggmovess = s;
						        		  }
						        		  else {
						        			  eggmovess += ", " + s;
						        		  }
									}
									
									for(String s:chainlist){
										if(chaineggmove.equals("")){
											chaineggmove = s;
						        		  }
						        		  else {
						        			  chaineggmove += ", " + s;
						        		  }
									}
   	  
									

										
									if(!moves.isEmpty()){
										result += "**Level-Up Moves: **\n"+moves+"\n\n";
									}
									if(!TM.isEmpty()){
										result += "**TMs: **\n"+TM+"\n\n";
									}
									if(!eggmoves.isEmpty()){
										System.out.println(eggmoves);
										result += "**Eggmoves: **\n"+eggmovess+"\n\n";
									}
									
									if(!chainlist.isEmpty()){
										System.out.println(chainlist);
										result += "**Chains: **\n"+chaineggmove+"\n\n";
									}
									
									if(result.equals("")){
										e.getChannel().sendMessage("No chain found.").queue();
									}
									else {
										e.getChannel().sendMessage("**"+poke+"**\n\n"+result).queue();
										System.out.println("**"+poke+"**\n\n"+result);
									}
									
									
									
								}
								
							}
							else {
								e.getChannel().sendMessage("Input invalid. Please try again.").queue();
							}
							
						}
						//command egg hatch game
						else if(message[0].equalsIgnoreCase("v!game")) {
							//start game
							if(message.length==2&&message[1].equals("start")){
								String userID = e.getMember().getUser().getId();
								
								JsonReader reader = new JsonReader(new FileReader("JSON/game.json"));
								Gson gson = new Gson();
								ArrayList<Game_user> users = new ArrayList<Game_user>() ;
								
								users = gson.fromJson(reader, new TypeToken<List<Game_user>>(){}.getType());
								
								System.out.println(users);
								if(checkUser(users, userID)){
									e.getChannel().sendMessage("Your account already exist!").queue();
								}
								else {
									Game_user new_user = createAccount(userID);
			
									addUser(users, new_user);
								}
								
								JsonReader newreader = new JsonReader(new FileReader("JSON/game.json"));
								Gson newgson = new Gson();
								ArrayList<Game_user> newusers = new ArrayList<Game_user>() ;
								
								newusers = newgson.fromJson(newreader, new TypeToken<List<Game_user>>(){}.getType());
								
								for(Game_user user:newusers){
									if(user.getUserID().equals(userID)){
										e.getChannel().sendMessage("**User: **"+e.getGuild().getMemberById(user.getUserID()).getEffectiveName()+"\n**Collection: **"+user.getCollection() + "\n **Steps: **" + user.getSteps()).queue();
									}
								}
								
		
			
							}
							else if (message.length==2&&message[1].equals("stat")){
								String userID = e.getAuthor().getId();
								JsonReader newreader = new JsonReader(new FileReader("JSON/game.json"));
								Gson newgson = new Gson();
								ArrayList<Game_user> newusers = new ArrayList<Game_user>() ;
								
								newusers = newgson.fromJson(newreader, new TypeToken<List<Game_user>>(){}.getType());
								
								for(Game_user user:newusers){
									if(user.getUserID().equals(userID)){
										e.getChannel().sendMessage("**User: **"+e.getGuild().getMemberById(user.getUserID()).getEffectiveName()+"\n**Collection: **"+user.getCollection() + ", **Current Steps: **" + user.getSteps()).queue();
									}
								}
							}
							
							
						}
						//command block/unblock tancoon
						else if(message[0].equalsIgnoreCase("v!block")) {
							if(message.length==2&&message[1].equals("block")){
								String userID = e.getMember().getUser().getId();
								
								JsonReader reader = new JsonReader(new FileReader("JSON/tancoonBlock.json"));
								Gson gson = new Gson();
								ArrayList<String> users = new ArrayList<String>() ;
								
								users = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
								
								boolean blockcheck = false;
								if(users!=null){
									for(String u: users) {
										if(u.equals(userID)){
											blockcheck = true;
											e.getChannel().sendMessage("You have blocked tancoon-bot already.").queue();
											break;
										}
									}
								}
								if(blockcheck == false){
									if(users==null){
										users = new ArrayList<String>() ;
									}
									users.add(userID);
									try (Writer writer = new FileWriter("JSON//tancoonBlock.json")) {
									    Gson wgson = new GsonBuilder().create();
									    wgson.toJson(users, writer);
									}
									e.getChannel().sendMessage("You blocked tancoon-bot.").queue();
								}
							}
							else if(message.length==2&&message[1].equals("unblock")){
								String userID = e.getMember().getUser().getId();
								
								JsonReader reader = new JsonReader(new FileReader("JSON/tancoonBlock.json"));
								Gson gson = new Gson();
								ArrayList<String> users = new ArrayList<String>() ;
								
								users = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
								
								boolean blockcheck = false;
								if(users!=null){
									for(String u: users) {
										if(u.equals(userID)){
											blockcheck = true;
											users.remove(u);
											try (Writer writer = new FileWriter("JSON//tancoonBlock.json")) {
											    Gson wgson = new GsonBuilder().create();
											    wgson.toJson(users, writer);
											}
											e.getChannel().sendMessage("You unblocked tancoon-bot.").queue();
											break;
										}
									}
								}
								if(blockcheck == false){
									e.getChannel().sendMessage("You have not blocked tancoon-bot yet.").queue();
								}
							}
						}
						//command treasure hunt
						else if(message[0].equalsIgnoreCase("v!hunt")) {
							if(message.length==2&&message[1].equals("start")){
								String userID = e.getAuthor().getId();
								
								JsonReader reader = new JsonReader(new FileReader("JSON/hunt.json"));
								Gson gson = new Gson();
								ArrayList<Hunt_user> users = new ArrayList<Hunt_user>() ;
								
								users = gson.fromJson(reader, new TypeToken<List<Hunt_user>>(){}.getType());
								
								if(users==null){
									Hunt_user new_user = new Hunt_user(userID);
									users = new ArrayList<Hunt_user>() ;
									
									addHuntUser(users, new_user);
								}
								else if(checkHuntUser(users, userID)){
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("Your account already exist!").queue();
				
								    });
								}
								else {
									Hunt_user new_user = new Hunt_user(userID);
			
									addHuntUser(users, new_user);
								}
								
								JsonReader newreader = new JsonReader(new FileReader("JSON/hunt.json"));
								Gson newgson = new Gson();
								ArrayList<Hunt_user> newusers = new ArrayList<Hunt_user>() ;
								
								newusers = newgson.fromJson(newreader, new TypeToken<List<Hunt_user>>(){}.getType());
								
								for(Hunt_user user:newusers){
									if(user.getUserID().equals(userID)){
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									        PrivateChannel channel = value; 
									        channel.sendMessage("Hey "+e.getAuthor().getName()+"! Thanks for playing the treasure hunt. You can use 'v!hunt help' to know more about it.").queue();
					
									    });
										
									}
								}
								
		
			
							}
						}
						
						//command fake ban
						else if(message[0].equalsIgnoreCase("v!ban")||message[0].equalsIgnoreCase("v!sacrifice")) {
							
							if(message.length>=2){
								System.out.println(message[1]);
								String userid = "";
								if(message[1].startsWith("<@")){
									userid = message[1].replaceAll("[^0-9]","");
									System.out.println(userid);
									
									if(userid.equals("175984908802457600")){
										userid=e.getAuthor().getId();
									}
									if(e.getGuild().getMemberById(userid)!=null){
										if(!e.getGuild().getMemberById(userid).getUser().isBot()) {
											String username;
											if(e.getGuild().getMemberById(userid).getNickname()!=null){
												username = e.getGuild().getMemberById(userid).getNickname();
											}
											else {
												username = e.getGuild().getMemberById(userid).getUser().getName();
											}
											
											String ban_message = "";
											if(message[0].equalsIgnoreCase("v!ban")){
												ban_message = message[1]+" is banned from the server. Bye bye "+username+". :wave: ";
											}
											else {
												ban_message = message[1]+" has been sacrificed to the RNG gods, bye bye "+username+". :wave: ";
											}
											
											
											if(message.length>=3){
												String reason = "";
												for(int i=2;i<message.length;i++){
													reason = reason + message[i]+" ";
												}
												ban_message = ban_message + "\n Reason: " + reason;
											}
											
											
												
											e.getChannel().sendMessage(ban_message).queue();
										}
										else {
											
											if(userid.equals("249187330731147264")){
												e.getChannel().sendMessage("Are you trying to get rid of me? Nice try. :kissing_heart:").queue();
											}
											else {
												e.getChannel().sendMessage("You cannot ban a bot. Please try again.").queue();
											}
										}
									}
									else {
										System.out.println(e.getGuild().getMemberById(userid));
										e.getChannel().sendMessage("User not found. Please try again.").queue();
									}
								}
								else {
									e.getChannel().sendMessage("Input invalid. Please try again.").queue();
								}
							}							
						}
						
						else{
							e.getChannel().sendMessage("Sorry, command invalid. Please check v!help for list of commands").queue();
						}
					}
				}
				else {
					if(!e.getAuthor().isBot()) {
						//command copy port
						if(message[0].equalsIgnoreCase("v!copyport")) {
								
								//221351400259584001 portfolio channel id
								e.getJDA().getGuildById("221317397653487626").getTextChannelById("221351400259584001").getHistory().retrievePast(100).queue((List<Message> messages) -> {

										Map<String, String> portlist= new LinkedHashMap<String, String>();
										
										
										for(Message m:messages) {
											
											String existing = portlist.get(m.getAuthor().getName());
											String extraContent = m.getContentRaw();
											portlist.put(m.getAuthor().getName(), existing == null ? extraContent : extraContent + "\n" + existing);
										}
										
										
										
										if(message.length == 1){
											ListIterator<Entry<String, String>> iter =
												    new ArrayList<>(portlist.entrySet()).listIterator(portlist.size());

											while (iter.hasPrevious()) {
											    Entry<String, String> entry = iter.previous();

											    String key = entry.getKey();
											    Object content = entry.getValue();
											    
											    String portmessage = "User: "+key+"\n```"+content+"```";

										    	e.getAuthor().openPrivateChannel().queue((value) ->
											    {
											        PrivateChannel channel = value; 
											        channel.sendMessage(portmessage).queue();
							
											    });
											}
										}
										else if(message.length>1){
												
											String userid = "";
											for(int i=1;i<message.length;i++) {
												userid += message[i];
											}
											
											String bestUser = "";
											double similarity = 0;
											ListIterator<Entry<String, String>> iter =
												    new ArrayList<>(portlist.entrySet()).listIterator(portlist.size());

											while (iter.hasPrevious()) {
											    Entry<String, String> entry = iter.previous();

											    String key = entry.getKey();											    
											    
											    if(similarity(key,userid)>similarity){
													similarity = similarity(key,userid);
													bestUser = key;
												}

											}
											if(portlist.get(bestUser)!=null&&similarity>0.9){
												String portmessage = "User: "+bestUser+"\n```"+portlist.get(bestUser)+" ```";

												e.getAuthor().openPrivateChannel().queue((value) ->
											    {
											        PrivateChannel channel = value; 
											        channel.sendMessage(portmessage).queue();
							
											    });

												
											}
											else {
												e.getAuthor().openPrivateChannel().queue((value) ->
											    {
											        PrivateChannel channel = value; 
											        channel.sendMessage("User not found.").queue();
							
											    });

											}
										}
								});
							
						}
						
						//command treasure hunt
						if(message[0].equalsIgnoreCase("v!hunt")) {
							if(message.length==2&&message[1].equals("start")){
								String userID = e.getAuthor().getId();
								
								JsonReader reader = new JsonReader(new FileReader("JSON/hunt.json"));
								Gson gson = new Gson();
								ArrayList<Hunt_user> users = new ArrayList<Hunt_user>() ;
								
								users = gson.fromJson(reader, new TypeToken<List<Hunt_user>>(){}.getType());
								
								if(users==null){
									Hunt_user new_user = new Hunt_user(userID);
									users = new ArrayList<Hunt_user>() ;
									
									addHuntUser(users, new_user);
								}
								else if(checkHuntUser(users, userID)){
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("Your account already exist!").queue();
				
								    });
								}
								else {
									Hunt_user new_user = new Hunt_user(userID);
			
									addHuntUser(users, new_user);
								}
								
								JsonReader newreader = new JsonReader(new FileReader("JSON/hunt.json"));
								Gson newgson = new Gson();
								ArrayList<Hunt_user> newusers = new ArrayList<Hunt_user>() ;
								
								newusers = newgson.fromJson(newreader, new TypeToken<List<Hunt_user>>(){}.getType());
								
								for(Hunt_user user:newusers){
									if(user.getUserID().equals(userID)){
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									        PrivateChannel channel = value; 
									        channel.sendMessage("Hey "+e.getAuthor().getName()+"! Thanks for playing the treasure hunt. You can use 'v!hunt help' to know more about it.").queue();
					
									    });
										
									}
								}
								
		
			
							}
							else if(message.length==2&&message[1].equals("help")){
								e.getAuthor().openPrivateChannel().queue((value) ->
							    {
							        PrivateChannel channel = value; 
							        channel.sendMessage("Thank you for joining the treasure hunt! \n\n"+
							        					"**commands:\n**"+
							        					"```v!hunt start - start the treasure hunt\n"+
							        					"v!hunt question - check the questions of the treasure hunt\n"+
							        					"v!hunt answer - check your submitted answers\n"+
							        					"v!hunt <question no.> <answer> - submit your answer based on the question no.```").queue();
			
							    });
							}
							else if(message.length>=3&&message[1].equals("add")){
								if(e.getAuthor().getId().equals("175984908802457600")){
									JsonReader qreader = new JsonReader(new FileReader("JSON/question.json"));
									Gson qgson = new Gson();
									ArrayList<String> questions = new ArrayList<String>() ;
														
									questions = qgson.fromJson(qreader, new TypeToken<List<String>>(){}.getType());
									
									String question = "";
									for(int i=2;i<message.length;i++){
										question+=message[i]+" ";
									}
									final String fquestion = question;
									questions.add(question);
									
									try (Writer qwriter = new FileWriter("JSON//question.json")) {
									    Gson wgson = new GsonBuilder().create();
									    wgson.toJson(questions, qwriter);
									}
									
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("Successfully added question: "+fquestion).queue();
				
								    });
									
									String text = "**Questions: **\n";
									for(int i=1;i<=questions.size();i++){
										text+=i+". "+questions.get(i-1)+"\n";
									}
									
									final String ftext = text; 
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage(ftext).queue();
				
								    });			
								}
								else {
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("Sorry, you do not have permission to perform this action.").queue();
				
								    });	
								}
								
							}
							else if(message.length==3&&message[1].equals("remove")){
								if(e.getAuthor().getId().equals("175984908802457600")){
									if(isStringInt(message[2])){
										JsonReader qreader = new JsonReader(new FileReader("JSON/question.json"));
										Gson qgson = new Gson();
										ArrayList<String> questions = new ArrayList<String>() ;
															
										questions = qgson.fromJson(qreader, new TypeToken<List<String>>(){}.getType());
										
										if(questions.size()>=Integer.parseInt(message[2])&&questions!=null){
											final String fquestion = questions.get(Integer.parseInt(message[2])-1);
											questions.remove(Integer.parseInt(message[2])-1);
											
											try (Writer qwriter = new FileWriter("JSON//question.json")) {
											    Gson wgson = new GsonBuilder().create();
											    wgson.toJson(questions, qwriter);
											}
											
											e.getAuthor().openPrivateChannel().queue((value) ->
										    {
										        PrivateChannel channel = value; 
										        channel.sendMessage("Successfully removed question: "+fquestion).queue();
						
										    });

											String text = "**Questions: **\n";
											for(int i=1;i<=questions.size();i++){
												text+=i+". "+questions.get(i-1)+"\n";
											}
											
											final String ftext = text; 
											e.getAuthor().openPrivateChannel().queue((value) ->
										    {
										        PrivateChannel channel = value; 
										        if(ftext!=""){
										        	channel.sendMessage(ftext).queue();
										        }
										        else {
										        	channel.sendMessage("There is no question at the moment.").queue();
										        }
										    });
										}
										else {
											e.getAuthor().openPrivateChannel().queue((value) ->
										    {
										        PrivateChannel channel = value; 
										        channel.sendMessage("Invalid input. Please make sure you have entered the correct question number.").queue();
										    });
										}
									}
									else {
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									        PrivateChannel channel = value; 
									        channel.sendMessage("Invalid input. Please try again").queue();
									    });
									}
								}
								else {
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("Sorry, you do not have permission to perform this action.").queue();
				
								    });	
								}
							}
							else if(message.length==2&&message[1].equals("question")){
								
								JsonReader qreader = new JsonReader(new FileReader("JSON/question.json"));
								Gson qgson = new Gson();
								ArrayList<String> questions = new ArrayList<String>() ;
								
								questions = qgson.fromJson(qreader, new TypeToken<List<String>>(){}.getType());

								if(questions==null){
									questions = new ArrayList<String>() ;
								}
								else {
									String text = "**Questions: **\n";
									for(int i=1;i<=questions.size();i++){
										text+=i+". "+questions.get(i-1)+"\n";
									}
									
									final String ftext = text; 
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        if(ftext!=""){
								        	channel.sendMessage(ftext).queue();
								        }
								        else {
								        	channel.sendMessage("There is no question at the moment.").queue();
								        }
								    });
								}							
							}
							else if(message.length==2&&message[1].equals("answer")){
								JsonReader qreader = new JsonReader(new FileReader("JSON/question.json"));
								Gson qgson = new Gson();
								ArrayList<String> questions = new ArrayList<String>() ;
								
								questions = qgson.fromJson(qreader, new TypeToken<List<String>>(){}.getType());
								
								String userID = e.getAuthor().getId();
								
								JsonReader reader = new JsonReader(new FileReader("JSON/hunt.json"));
								Gson gson = new Gson();
								ArrayList<Hunt_user> users = new ArrayList<Hunt_user>() ;
								
								users = gson.fromJson(reader, new TypeToken<List<Hunt_user>>(){}.getType());
								
								if(users==null){
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("You havn't start the hunt yet! Please use 'v!hunt start' to start").queue();
				
								    });
								}
								else if(!checkHuntUser(users, userID)){
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("You havn't start the hunt yet! Please use 'v!hunt start' to start").queue();
				
								    });
								}
								else {
									for(Hunt_user user:users){
										
										
										if(user.getUserID().equals(userID)){
											Map<Integer, String> answers = user.getAnswers();
											
											String text ="**Answers: **\n";
											for(int i=1;i<=questions.size();i++){
												if(answers.get(i)!=null){
													text+=i+". "+answers.get(i)+"\n";
												}
												else {
													text+=i+". Not yet answered.\n";
												}
												
											}
											
											final String ftext = text;
											e.getAuthor().openPrivateChannel().queue((value) ->
										    {
										        PrivateChannel channel = value; 
										        channel.sendMessage(ftext).queue();
						
										    });
										}
									}
								}
							}
							else if(message.length>=3){
								if(isStringInt(message[1]))
								{
									JsonReader qreader = new JsonReader(new FileReader("JSON/question.json"));
									Gson qgson = new Gson();
									ArrayList<String> questions = new ArrayList<String>() ;
									
									questions = qgson.fromJson(qreader, new TypeToken<List<String>>(){}.getType());
									
									String userID = e.getAuthor().getId();
									
									JsonReader reader = new JsonReader(new FileReader("JSON/hunt.json"));
									Gson gson = new Gson();
									ArrayList<Hunt_user> users = new ArrayList<Hunt_user>() ;
									
									users = gson.fromJson(reader, new TypeToken<List<Hunt_user>>(){}.getType());
									
									if(users==null){
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									        PrivateChannel channel = value; 
									        channel.sendMessage("You havn't start the hunt yet! Please use 'v!hunt start' to start").queue();
					
									    });
									}
									else if(!checkHuntUser(users, userID)){
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									        PrivateChannel channel = value; 
									        channel.sendMessage("You havn't start the hunt yet! Please use 'v!hunt start' to start").queue();
					
									    });
									}
									else if(Integer.parseInt(message[1])>questions.size()){
										e.getAuthor().openPrivateChannel().queue((value) ->
									    {
									        PrivateChannel channel = value; 
									        channel.sendMessage("Invalid input. Please make sure you have entered the correct question number.").queue();
					
									    });
									}						
									else {
										
										String answer = "";
										for(int i=2;i<message.length;i++){
											answer+=message[i]+" ";
										}
										
										ArrayList<Hunt_user> newusers = new ArrayList<Hunt_user>();
										for(Hunt_user user:users){
											
											
											if(user.getUserID().equals(userID)){
												Map<Integer, String> answers = user.getAnswers();
												
												answers.put(Integer.parseInt(message[1]), answer);
												
												user.setAnswers(answers);
												
												String text ="**Answers: **\n";
												for(int i=1;i<=questions.size();i++){
													if(answers.get(i)!=null){
														text+=i+". "+answers.get(i)+"\n";
													}
													else {
														text+=i+". Not yet answered.\n";
													}
													
												}
												
												final String ftext = text;
												e.getAuthor().openPrivateChannel().queue((value) ->
											    {
											        PrivateChannel channel = value; 
											        channel.sendMessage(ftext).queue();
							
											    });
											}
											newusers.add(user);
										}
										
										try (Writer writer = new FileWriter("JSON//hunt.json")) {
										    Gson wgson = new GsonBuilder().create();
										    wgson.toJson(users, writer);
										}
									}
								}
								else {
									e.getAuthor().openPrivateChannel().queue((value) ->
								    {
								        PrivateChannel channel = value; 
								        channel.sendMessage("Input Invalid. Please try again.").queue();
				
								    });
								}
							}
							else {
								e.getAuthor().openPrivateChannel().queue((value) ->
							    {
							        PrivateChannel channel = value; 
							        channel.sendMessage("Input Invalid. Please try again.").queue();
			
							    });
							}
							
						}
						
						//301740745990078465 Vyhn bot private channel id
						if(e.getChannel().getId().equals("301740745990078465")){
							System.out.println(e.getMessage().getContentRaw());
							String[] messages = e.getMessage().getContentRaw().split(" ");
							if(messages[0].equals("v!talk")){
								//223126199784701952 PU breeder guild bot_spam id
								String channel = "223126199784701952";
								switch (messages[1]){
									case "general":
										//221317397653487626 PU breeder guild general id
										channel = "221317397653487626";
										break;
									case "bot":
										//223126199784701952 PU breeder guild bot_spam id
										channel = "223126199784701952";
										break;
									case "offtopic":
										//223052516319952896 PU breeder guild off topic id
										channel = "223052516319952896";
										break;
									default:
										//223126199784701952 PU breeder guild bot_spam id
										channel = "223126199784701952";
										break;
								}
								
								String m = "";
								for(int i=2;i<messages.length;i++){
									if(m.equals("")){
										m = messages[i];
									}
									else {
										m = m + " " + messages[i];
									}
								}
								e.getJDA().getTextChannelById(channel).sendMessage(m).queue();
							}
						}
						else {
							//249187330731147264 Vyhn bot private channel id
							System.out.println(e.getChannel().getId());
							e.getJDA().getUserById("175984908802457600").openPrivateChannel().queue((value) ->
						    {
						        PrivateChannel channel = value; 
						        channel.sendMessage("**User: **"+e.getAuthor().getName()+"\n"+e.getMessage().getContentRaw()).queue();
		
						    });
							//e.getJDA().getPrivateChannelById("249187330731147264").sendMessage().queue();
						}
						
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private boolean checkUser(ArrayList<Game_user> users, String id) throws IOException{
	
		if(!users.isEmpty()){
			for(Game_user user:users){
				if(user.getUserID().equals(id)){
					return true;
					
				}
			}
		}
		
		
		return false;
		
		
	}
	private boolean checkHuntUser(ArrayList<Hunt_user> users, String id) throws IOException{
	
		
		for(Hunt_user user:users){
			if(user.getUserID().equals(id)){
				return true;
				
			}
		}
		
		return false;
		
		
	}
	
	private Game_user createAccount(String id) throws FileNotFoundException{
		ArrayList<String> mCollection = new ArrayList<String>();

		Game_user user = new Game_user(id, mCollection, false, 0, generateEgg());
				
		return user;
	}
	
	private Egg generateEgg() throws FileNotFoundException{
		String eggPoke = randPokemon();
		int eggStep =  Integer.parseInt(convertpokestring(eggPoke).getEggsteps()); 
		Egg egg = new Egg(eggPoke, eggStep, generateShiny());
		
		return egg;
	}
	
	private String randPokemon() throws FileNotFoundException{
		boolean pokecheck = false;
		String pokename = "";
		
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>();
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		
		while(!pokecheck){
			int pokenum = 1 + (int)(Math.random() * 200);
			if(pokemons.get(pokenum).getEvo().equals("1")&&!pokemons.get(pokenum).getEgggroup().equals("Undiscovered")&&!pokemons.get(pokenum).getEgggroup().equals("ditto")){
				pokename = pokemons.get(pokenum).getName();
				pokecheck = true;
			}
		}
		
		return pokename;
	}
	
	private void addUser(ArrayList<Game_user> users, Game_user user) throws IOException{

		users.add(user);
		
		try (Writer writer = new FileWriter("JSON//game.json")) {
		    Gson wgson = new GsonBuilder().create();
		    wgson.toJson(users, writer);
		}
		
		System.out.println(users);
	}
	
	private void addHuntUser(ArrayList<Hunt_user> users, Hunt_user user) throws IOException{

		users.add(user);
		
		try (Writer writer = new FileWriter("JSON//hunt.json")) {
		    Gson wgson = new GsonBuilder().create();
		    wgson.toJson(users, writer);
		}
		
		System.out.println(users);
	}
	
	private String getHp(String[] e){
		int[] stat = new int[6];
		for(int i=1;i<e.length;i++) {
			if((Integer.parseInt(e[i])%2)==0)
				stat[i-1] = 0;
			else
				stat[i-1] = 1;
		}

		int type =  ((stat[0] + 2*stat[1] + 4*stat[2] + 8*stat[3] + 16*stat[4] + 32*stat[5])*16/63);
		
		switch (type) {
			case 0:
				return "Fighting";
			case 1:
				return "Flying";
			case 2:
				return "Rock";
			case 3:
				return "Bug";
			case 4:
				return "Fire";
			case 5:
				return "Water";
			case 6:
				return "Psychic";
			case 7:
				return "Ice";
			case 8:
				return "Poison";
			case 9:
				return "Ground";
			case 10:
				return "Ghost";
			case 11:
				return "Steel";
			case 12:
				return "Grass";
			case 13:
				return "Electric";
			case 14:
				return "Dragon";
			case 15:
				return "Dark";
			case 16:
				return "Fairy";	
			default:
				return "Error";
			
		}
	}
	
	private boolean checkmove(String eggmove) {
		for(String s:move){
			if(eggmove.replaceAll("\\-|\\s+","").equalsIgnoreCase(s.replaceAll("\\-|\\s+",""))){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkpoke(String poke) throws FileNotFoundException{
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>() ;
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		for(pokemon p:pokemons){
			if(p.getName().replaceAll("\\-|\\s+","").equalsIgnoreCase(poke.replaceAll("\\-|\\s+",""))){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkeggmove(String poke, String eggmove) throws FileNotFoundException{
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>() ;
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		for(pokemon p:pokemons){
			if(p.getName().equalsIgnoreCase(poke)){
				String[] eggmoves = p.getEggmoves().split(",");
				for(String e: eggmoves){
					if(e.replaceAll("\\-|\\s+","").equalsIgnoreCase(eggmove.replaceAll("\\-|\\s+",""))){
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/*
	private String generatechainlist(String chainlist, String poke, Map<String, ArrayList<String>> chains){
		for (Map.Entry<String, ArrayList<String>> pair : chains.entrySet()) {
			String key = pair.getKey();
			ArrayList<String> list = pair.getValue();
			
				System.out.println("chainlist: "+chainlist+" poke: "+poke);
			   //if()
	    	   if(chains.get(poke+"(move)")!=null&&key.equalsIgnoreCase(poke+"(move)")) {
	    		   
	    		   String templist = poke + "(lvl) -> "+ chainlist;
				   chainlist = "";
				   for(String s: chains.get(poke+"(move)")){
					   if(chainlist.equals("")){
						   chainlist += s + "->" + templist;
					   }
					   else {
						   chainlist += ", " + s + "->" + templist;
					   }
					   
				   }
	    		  
				   
	    	   }
	    	   if(chains.get(poke+"(tm)")!=null&&key.equalsIgnoreCase(poke+"(tm)")) {
	    		   String templist = poke + "(tm) -> "+ chainlist;
				   chainlist = "";
				   for(String s: chains.get(poke+"(tm)")){
					   if(chainlist.equals("")){
						   chainlist += s + "->" + templist;
					   }
					   else {
						   chainlist += ", " + s + "->" + templist;
					   }
					   
				   }
	
			   }
	    	   for(String value: list) {
	    	   if(chains.get(poke+"(eggmove)")!=null&&key.equalsIgnoreCase(poke+"(eggmove)")){
	
	    		   String next = value.toString();
	
	    		   if((chains.containsKey(next+"(move)")||chains.containsKey(next+"(tm)")||chains.containsKey(next+"(eggmove)"))&&!poke.equalsIgnoreCase(value.toString())) {
	    			   System.out.println("generate: "+value.toString());
	    			   return generatechainlist(chainlist, value.toString(), chains);
	    		   }
				   
			   }
	        	   
	           
	        }
	    }
		return chainlist;
			  
			  
	}*/
	
	private Map<String, ArrayList<String>> searcheggmove(pokemon opoke, String eggmove, Map<String, ArrayList<String>> chain) throws FileNotFoundException {
		
	
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>() ;
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		/*
		if(geteggfrommove(pokemons, opoke, eggmove).size()>0) {
			chain.put(opoke.getName()+"(move)", geteggfrommove(pokemons, opoke, eggmove));
			System.out.println("Current: "+opoke.getName()+" (add move)");
		}
		if(geteggfromtm(pokemons, opoke, eggmove).size()>0) {
			chain.put(opoke.getName()+"(tm)", geteggfromtm(pokemons, opoke, eggmove));
			System.out.println("Current: "+opoke.getName()+" (add tm)");
			
		}
		if(geteggfromeggmove(pokemons, opoke, eggmove).size()>0) {
			
			chain.put(opoke.getName()+"(eggmove)", geteggfromeggmove(pokemons, opoke, eggmove));
			System.out.println(geteggfromeggmove(pokemons, opoke, eggmove));			
		}
		return chain;*/
		
		if(geteggfrommove(pokemons, opoke, eggmove).size()==0&&geteggfromtm(pokemons, opoke, eggmove).size()==0&&geteggfromeggmove(pokemons, opoke, eggmove).size()==0) {
			System.out.println("Current: "+opoke.getName()+" (end)");
			return chain;
		}
		else if(geteggfrommove(pokemons, opoke, eggmove).size()==0&&geteggfromtm(pokemons, opoke, eggmove).size()==0&&geteggfromeggmove(pokemons, opoke, eggmove).size()>0) {
			
			chain.put(opoke.getName()+"(eggmove)", geteggfromeggmove(pokemons, opoke, eggmove));
			System.out.println(geteggfromeggmove(pokemons, opoke, eggmove));
			for(String s: geteggfromeggmove(pokemons, opoke, eggmove)){
				ArrayList<String> value = chain.get(s+"(eggmove)");
				if (value == null) {
					Map<String, ArrayList<String>> tchain = new HashMap<String, ArrayList<String>>();
					tchain = searcheggmove(convertpokestring(s),eggmove,chain);
					for (Map.Entry<String, ArrayList<String>> pair : tchain.entrySet()) {
						chain.put(pair.getKey(), pair.getValue());
						
					}
				}
			
				
				
			}	
			return chain;
			
		}
		else {
			if(geteggfrommove(pokemons, opoke, eggmove).size()>0) {
				chain.put(opoke.getName()+"(move)", geteggfrommove(pokemons, opoke, eggmove));
				System.out.println("Current: "+opoke.getName()+" (add move)");
			}
			
			if(geteggfromtm(pokemons, opoke, eggmove).size()>0) {
				chain.put(opoke.getName()+"(tm)", geteggfromtm(pokemons, opoke, eggmove));
				System.out.println("Current: "+opoke.getName()+" (add tm)");
				
			}
			return chain;
		}

	}
	
	private ArrayList<String> geteggfrommove(ArrayList<pokemon> pokemons, pokemon opoke, String eggmove) {
		
		String[] ogroup = opoke.getEgggroup().split(",");
		ArrayList<String> chain = new ArrayList<String>();
		for(pokemon npoke: pokemons) {
			String[] ngroup = npoke.getEgggroup().split(",");
			for(int i=0;i<ogroup.length;i++) {
				for(int j=0;j<ngroup.length;j++){
					if(ogroup[i].equals(ngroup[j])){
						if(!npoke.getGender().equals("0")) {
							if(npoke.getEvo().equals("1")){
								String[] nmove = npoke.getMoves().split(",");
								for(String s:nmove){
									if(s.replaceAll("\\-|\\s+","").equalsIgnoreCase(eggmove.replaceAll("\\-|\\s+",""))) {
										chain.add(npoke.getName());
									}
								}
							}
							
						}
					}
				}
			}
		}
		
		Set<String> hs = new LinkedHashSet<>(chain);
		hs.addAll(chain);
		chain.clear();
		chain.addAll(hs);
		
		return chain;
	}

	
	private ArrayList<String> geteggfromtm(ArrayList<pokemon> pokemons, pokemon opoke, String eggmove) {
		
		String[] ogroup = opoke.getEgggroup().split(",");
		ArrayList<String> chain = new ArrayList<String>();
		for(pokemon npoke: pokemons) {
			String[] ngroup = npoke.getEgggroup().split(",");
			for(int i=0;i<ogroup.length;i++) {
				for(int j=0;j<ngroup.length;j++){
					if(ogroup[i].equals(ngroup[j])){
						if(!npoke.getGender().equals("0")) {
							if(npoke.getEvo().equals("1")){
								if(npoke.getTM()!=null){
									String[] ntm = npoke.getTM().split(",");
								
									for(String s:ntm){
										if(s.replaceAll("\\-|\\s+","").equalsIgnoreCase(eggmove.replaceAll("\\-|\\s+",""))) {
											chain.add(npoke.getName());
										}
									}
								}
							}
						}
					}
				}
			}	
		}
		
		Set<String> hs = new LinkedHashSet<>(chain);
		hs.addAll(chain);
		chain.clear();
		chain.addAll(hs);
		
		return chain;
	}
	
	private ArrayList<String> geteggfromeggmove(ArrayList<pokemon> pokemons, pokemon opoke, String eggmove) {
		
		String[] ogroup = opoke.getEgggroup().split(",");
		ArrayList<String> chain = new ArrayList<String>();
		for(pokemon npoke: pokemons) {
			String[] ngroup = npoke.getEgggroup().split(",");
			for(int i=0;i<ogroup.length;i++) {
				for(int j=0;j<ngroup.length;j++){
					if(ogroup[i].equals(ngroup[j])){
						if(!npoke.getGender().equals("0")) {
							if(npoke.getEvo().equals("1")){
								if(npoke.getEggmoves()!=null){
									String[] neggmove = npoke.getEggmoves().split(",");
								
									for(String s:neggmove){
										if(s.replaceAll("\\-|\\s+","").equalsIgnoreCase(eggmove.replaceAll("\\-|\\s+",""))) {
											chain.add(npoke.getName());
											System.out.print("add eggmove: "+npoke.getName()+chain);
										}
									}
								}
							}
						}
						
					}
				}
			}
		}
		
		Set<String> hs = new LinkedHashSet<>(chain);
		hs.addAll(chain);
		chain.clear();
		chain.addAll(hs);
		
		return chain;
	}
	
	private pokemon convertpokestring(String s) throws FileNotFoundException{
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>() ;
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		for(pokemon p: pokemons){
			if(s.replaceAll("\\-|\\s+","").equalsIgnoreCase(p.getName().replaceAll("\\-|\\s+",""))) {
				return p;
			}
		}
		return null;
	}
	
	private String convertpokenum(String poke) throws FileNotFoundException {
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>() ;
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		for(pokemon p: pokemons){
			if(p.getNdex().equals(poke)) {
				return p.getName();
			}
		}
		return null;
	}
	
	private String getPokeStat(String[] message, boolean s) throws FileNotFoundException {
				
		String pokemon = message[1];
		String type = "poke";
		
		if(message.length>1){
			if(message[1].equalsIgnoreCase("Aiden")){
				pokemon = "tancoon";
			}
			else if(message[1].equalsIgnoreCase("vyhn")||message[1].equalsIgnoreCase("snek")){
				pokemon = "ekans";
			}
			else if(message[1].equalsIgnoreCase("gin")){
				pokemon = "snowpach";
			}
			else if(message[1].equalsIgnoreCase("bay")){
				pokemon = "gargryph";
			}
			else if(message[1].equalsIgnoreCase("journey")){
				pokemon = "eletux";
			}
			else if(message[1].equalsIgnoreCase("s51-a")){
				pokemon = "s51a";
			}
		}
		
		if(message.length==3){
			switch(message[2].toLowerCase()){
				case "stats":
					type = "stats";
					break;
				case "abilities":
					type = "abilities";
					break;
				case "gender":
					type = "gender";
					break;
				case "egggroups":
					type = "egggroups";
					break;
				case "eggmoves":
					type = "eggmoves";
					break;
				case "tms":
					type = "tm";
					break;
				case "moves":
					type = "move";
					break;
				default:
					type = "error";
					
			}
		}
		else {
			
		}
		
		if(s&&message.length==2){
			return ":sleeping::zzz:...go talk to neo-bot...\n```\n>dex "+pokemon+"\n```";
		}
		else {
			return generateText(pokemon, type);
		}
		
		
	}
	
	private String generateText(String p, String type) throws FileNotFoundException{
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>();
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		String stat = null;
		double similarity = 0;
		String bestPoke = "";
		if(!isStringInt(p)) {
			for(pokemon pokemon: pokemons){
				if(similarity(pokemon.getName(),p)>similarity){
					similarity = similarity(pokemon.getName(),p);
					bestPoke = pokemon.getName();
				}
			}
			System.out.println("similarity: "+similarity+" poke: "+bestPoke);
			if(similarity<=0.7){
				stat = "Sorry, pokemon not found. Are you sure it is spelled correctly?\n";
				if(!guessName(p.substring(0, 1)).equals("")){
					stat = stat + "Are you searching for these pokemon?\n" + guessName(p.substring(0, 1));
				}
				return stat;
			}
		}
		else {
			if(Integer.parseInt(p)<=0||Integer.parseInt(p)>200){
				return "Dex number should be between 1 and 200. Please try again.";
			}
		}
			
		for(pokemon pokemon: pokemons){
			if(bestPoke.equalsIgnoreCase(pokemon.getName())||p.equalsIgnoreCase(pokemon.getNdex())){
				switch(type){
					case "poke":
						System.out.println(pokemon.toString());
						stat = "**Dex No. "+pokemon.getNdex()+"**\n"+
								"**Name:** "+pokemon.getName()+"\n";
						
						//types
						String[] types = pokemon.getType().split(",");
					
						for(int i=1;i<=types.length;i++){
							if(types[i-1]!=null){
								stat = stat + "**Type "+i+":**  "+types[i-1]+"\n";
							}
						}
						
						stat = stat+"\n"+
								"**Base Stats**\n"+
								"**HP:** "+pokemon.getHp()+"\n"+
								"**Attack:** "+pokemon.getAt()+"\n"+
								"**Defense:** "+pokemon.getDf()+"\n"+
								"**Sp. Atk:** "+pokemon.getSa()+"\n"+
								"**Sp. Def:** "+pokemon.getSd()+"\n"+
								"**Speed:** "+pokemon.getSp()+"\n"+
								"\n";
								
								//gender ratio
								/* genderless: 0
								 * M 87.5, F 12.5: 1
								 * M 75, F 25: 2
								 * M 50, F 50: 3
								 * M 25, F 75: 4
								 * M 0, F 100: 5
								 * */
								 
								 stat = stat+"**Gender Ratio**\n";
								 switch(pokemon.getGender()) {
								 
								 case "0":
									 stat = stat+"Genderless\n"+
												"\n";
									 break;
									 
								 case "1":
									 stat = stat+"**Male: ** 87.5%\n"+
												"**Female: ** 12.5%\n"+
												"\n";
									 break;
									 
								 case "2":
									 stat = stat+"**Male:** 75%\n"+
												"**Female** 25%\n"+
												"\n";
									 break;
									 
								 case "3":
									 stat = stat+"**Male:** 50%\n"+
												"**Female** 50%\n"+
												"\n";
									 break;
									 
								 case "4":
									 stat = stat+"**Male:** 25%\n"+
												"**Female** 75%\n"+
												"\n";
									 break;
									 
								 case "5":
									 stat = stat+"**Female** 100%\n"+
												"\n";
									 break;
									 
								 default:
									 break;
								 }

								
								//Abilities
						 stat = stat+"**Abilities**\n";
						String[] abilities = pokemon.getAbility().split(",");
						if(pokemon.getHa().equals("0")){
							for(int i=1;i<=abilities.length;i++){
								if(abilities[i-1]!=null){
									stat = stat + "**Ability "+i+":**  "+abilities[i-1]+"\n";
								}
							}
						}
						else if(pokemon.getHa().equals("1")) {
							for(int i=1;i<abilities.length;i++){
								if(abilities[i-1]!=null){
									stat = stat + "**Ability "+i+":**  "+abilities[i-1]+"\n";
								}
							}
							stat = stat + "**Hidden Ability:** "+abilities[abilities.length-1]+"\n";
						}
								
								
								stat = stat + "\n" +
								
								//Egg moves
								//"**Egg Moves**\n"+pokemon.getEggmoves() + "\n\n" +
								
								//Egg groups
								"**Egg Group**\n";
								String[] eggGroups = pokemon.getEgggroup().split(",");
								for(int i=1;i<=eggGroups.length;i++){
									stat = stat + "**Group "+i+":**  "+eggGroups[i-1]+"\n";
								}
								
						stat = stat +"\n"+
								"**Steps To Hatch:** "+pokemon.getEggsteps()+"\n"+
								"\n"/*+
								pokemon.getImg()*/ +
								pokemon.getUrl();
						break;
						
					case "stats":
						stat =  "**Base Stats**\n"+
								"**HP:** "+pokemon.getHp()+"\n"+
								"**Attack:** "+pokemon.getAt()+"\n"+
								"**Defense:** "+pokemon.getDf()+"\n"+
								"**Sp. Atk:** "+pokemon.getSa()+"\n"+
								"**Sp. Def:** "+pokemon.getSd()+"\n"+
								"**Speed:** "+pokemon.getSp();
						break;
					case "abilities":
						stat ="**Abilities**\n";
						String[] abilities_ = pokemon.getAbility().split(",");
						if(pokemon.getHa().equals("0")){
							for(int i=1;i<=abilities_.length;i++){
								if(abilities_[i-1]!=null){
									stat = stat + "**Ability "+i+":**  "+abilities_[i-1]+"\n";
								}
							}
						}
						else if(pokemon.getHa().equals("1")) {
							for(int i=1;i<abilities_.length;i++){
								if(abilities_[i-1]!=null){
									stat = stat + "**Ability "+i+":**  "+abilities_[i-1]+"\n";
								}
							}
							stat = stat + "**Hidden Ability:** "+abilities_[abilities_.length-1]+"\n";
						}
						break;
					case "gender":
						stat = "**Gender Ratio**\n";
						 switch(pokemon.getGender()) {
						 
						 case "0":
							 stat = "Genderless\n"+
										"\n";
							 break;
							 
						 case "1":
							 stat = stat+"**Male:** 87.5%\n"+
										"**Female** 12.5%\n"+
										"\n";
							 break;
							 
						 case "2":
							 stat = stat+"**Male:** 75%\n"+
										"**Female** 25%\n"+
										"\n";
							 break;
							 
						 case "3":
							 stat = stat+"**Male:** 50%\n"+
										"**Female** 50%\n"+
										"\n";
							 break;
							 
						 case "4":
							 stat = stat+"**Male:** 25%\n"+
										"**Female** 75%\n"+
										"\n";
							 break;
							 
						 case "5":
							 stat = stat+"**Female** 100%\n"+
										"\n";
							 break;
							 
						 default:
							 break;
						 }
						 break;
					case "egggroups":
						stat = "**Egg Group**\n";
						String[] eggGroups_ = pokemon.getEgggroup().split(",");
						for(int i=1;i<=eggGroups_.length;i++){
							stat = stat + "**Group "+i+":**  "+eggGroups_[i-1]+"\n";
						}
						break;
					case "eggmoves":
						stat = "**Egg Moves**\n"+pokemon.getEggmoves();
						break;
					case "tm":
						stat = "**TMs**\n"+pokemon.getTM();
						break;
					case "move":
						stat = "**Level-up moves**\n"+pokemon.getMoves();
						break;
					case "error":
						stat = "Command invalid. Please try again";
						break;
					default:
						break;
					}
				
				}
				
		}
		
		if(stat == null) {
			stat = "Sorry, pokemon not found. Are you sure it is spelled correctly?\n";
			if(!guessName(p.substring(0, 1)).equals("")){
				stat = stat + "Are you searching for these pokemon?\n" + guessName(p.substring(0, 1));
			}
		}
		return stat;
	}
	
	private synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
	    long guildId = Long.parseLong(guild.getId());
	    GuildMusicManager musicManager = musicManagers.get(guildId);

	    if (musicManager == null) {
	      musicManager = new GuildMusicManager(playerManager);
	      musicManagers.put(guildId, musicManager);
	    }
	    musicManager.player.setVolume(1);
	    guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

	    return musicManager;
	  }

	private void loadAndPlay(final TextChannel channel, final String trackUrl) {
	    GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());

	    playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
	      @Override
	      public void trackLoaded(AudioTrack track) {
	        channel.sendMessage("Adding to queue " + track.getInfo().title).queue();

	        play(channel.getGuild(), musicManager, track);
	      }

	      public void playlistLoaded(AudioPlaylist playlist) {
	        //AudioTrack firstTrack = playlist.getSelectedTrack();
	        
	        for(AudioTrack a: playlist.getTracks()){
	        	musicManager.scheduler.queue(a);
	        }

	        channel.sendMessage("Adding to queue (Playlist) " + playlist.getName()).queue();

	        connectToFirstVoiceChannel(channel.getGuild().getAudioManager());
	        
	      }

	      public void noMatches() {
	        channel.sendMessage("Nothing found by " + trackUrl).queue();
	      }

	      public void loadFailed(FriendlyException exception) {
	        channel.sendMessage("Could not play: " + exception.getMessage()).queue();
	      }
	    });
	  }
	
	  private void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
	    connectToFirstVoiceChannel(guild.getAudioManager());
	
	    musicManager.scheduler.queue(track);
	    
	  }
	  
	  private void pause(TextChannel channel) {
		  GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
		  musicManager.player.setPaused(!musicManager.player.isPaused());
		  
		  if(musicManager.player.isPaused()){
			  channel.sendMessage("Player is now paused.").queue();
		  }
		  else {
			  channel.sendMessage("Player is now unpaused.").queue();
		  }
		    
	  }
	
	  private void skipTrack(TextChannel channel) {
	    GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
	    musicManager.scheduler.nextTrack();
	
	    channel.sendMessage("Skipped to next track.").queue();
	  } 
	  private void changeVolume(int volume, TextChannel channel) {
		  GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
		  musicManager.player.setVolume(volume);
		  channel.sendMessage("Volume set to "+volume).queue();
	  }
	  
	  private static void connectToFirstVoiceChannel(AudioManager audioManager) {
	    if (!audioManager.isConnected() && !audioManager.isAttemptingToConnect()) {
	      for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
	        audioManager.openAudioConnection(voiceChannel);
	        break;
	      }
	    }
	  }
	  
	private String guessName(String c) throws FileNotFoundException{
		JsonReader reader = new JsonReader(new FileReader("JSON/pokemon.json"));
		Gson gson = new Gson();
		ArrayList<pokemon> pokemons = new ArrayList<pokemon>();
		pokemons = gson.fromJson(reader, new TypeToken<List<pokemon>>(){}.getType());
		
		String names = "";
		
		for(pokemon pokemon: pokemons){
			if(pokemon.getName().startsWith(c.toUpperCase())) {
				if(names.equals("")){
					names = pokemon.getName();
				}
				else {
					names = names + ", " + pokemon.getName();
				}
				
			}
		}
		
		return names;
		
	}
	
	public boolean generateShiny() {
		
		boolean isShiny;
		if((int)(Math.random() * 1000)>SHINYRATE) {
			isShiny = false;
		}
		else {
			isShiny = true;
		}
		
		return isShiny;
	}
	
	public boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	
	/**
	 * Calculates the similarity (a number within 0 and 1) between two strings.
	 */
	public static double similarity(String s1, String s2) {
	  String longer = s1, shorter = s2;
	  if (s1.length() < s2.length()) { // longer should always have greater length
	    longer = s2; shorter = s1;
	  }
	  int longerLength = longer.length();
	  if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
	  return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
	}
	// you can use StringUtils.getLevenshteinDistance() as the editDistance() function
	// full copy-paste working code is below
	
	 // Example implementation of the Levenshtein Edit Distance
	  // See http://rosettacode.org/wiki/Levenshtein_distance#Java
	  public static int editDistance(String s1, String s2) {
	    s1 = s1.toLowerCase();
	    s2 = s2.toLowerCase();

	    int[] costs = new int[s2.length() + 1];
	    for (int i = 0; i <= s1.length(); i++) {
	      int lastValue = i;
	      for (int j = 0; j <= s2.length(); j++) {
	        if (i == 0)
	          costs[j] = j;
	        else {
	          if (j > 0) {
	            int newValue = costs[j - 1];
	            if (s1.charAt(i - 1) != s2.charAt(j - 1))
	              newValue = Math.min(Math.min(newValue, lastValue),
	                  costs[j]) + 1;
	            costs[j - 1] = lastValue;
	            lastValue = newValue;
	          }
	        }
	      }
	      if (i > 0)
	        costs[s2.length()] = lastValue;
	    }
	    return costs[s2.length()];
	  }

}
