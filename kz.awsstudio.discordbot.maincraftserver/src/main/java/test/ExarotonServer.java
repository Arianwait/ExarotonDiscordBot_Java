package test;

import java.util.Arrays;

import com.exaroton.api.APIException;
import com.exaroton.api.ExarotonClient;
import com.exaroton.api.server.Server;
import com.exaroton.api.server.ServerStatus;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ExarotonServer {

    private final Server server;

    // Замените на ваш реальный API токен
    private final String apiToken = "Exaroton-token";

    // Замените на ID вашего сервера
    private final String serverId = "serverID"; 

    public ExarotonServer() {
        // Создаем клиент
        ExarotonClient client = new ExarotonClient(apiToken);

        // Получаем сервер
        server = client.getServer(serverId);
        
        try {
            // Получаем информацию о сервере
            server.get();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void serverStart(MessageReceivedEvent event) {
        try {
            // Получаем информацию о сервере
            server.get();

            // Проверяем статус сервера
            int status = server.getStatus();

            // Запускаем сервер, если он не запущен
            if (status == ServerStatus.OFFLINE) {
                event.getChannel().sendMessage("Запуск сервера...").queue();
                server.start();
                while(ServerStatus.ONLINE != status) {
                	server.get();
                	status = server.getStatus();
                	try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                event.getChannel().sendMessage("Сервер запущен.").queue();
            } else {
                event.getChannel().sendMessage("Сервер уже работает.").queue();
            }
        } catch (APIException e) {
            event.getChannel().sendMessage("Ошибка выполнения команды, обратитесь к администратору").queue();
            e.printStackTrace();
        }
    }

    public void serverEND(MessageReceivedEvent event) {
        try {
            // Получаем информацию о сервере
            server.get();
            
            // Проверяем статус сервера
            int status = server.getStatus();
            
            // Остановка сервера
            
            if (status == ServerStatus.ONLINE) {
            	if(server.getPlayerInfo().getCount() == 0) {
                    // String response = server.runCommand("list");
                    String[] i =  server.getPlayerLists();
                   
                    System.out.print( Arrays.toString(i));
                    // Используем регулярное выражение для извлечения количества игроков
                   // Pattern pattern = Pattern.compile("There are (\\d+) of a max (\\d+) players online");
                   // Matcher matcher = pattern.matcher(response);
                    
                event.getChannel().sendMessage("Остановка сервера...").queue();
                server.stop();
                while(ServerStatus.OFFLINE != status) {
                	server.get();
                	status = server.getStatus();
                	try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                event.getChannel().sendMessage("Сервер остановлен.").queue();
               
            	} else {
               	 event.getChannel().sendMessage("В данный момент на сервере есть игроки").queue();
               }
            	} else {
                event.getChannel().sendMessage("Сервер уже остановлен.").queue();
            }
            
        } catch (APIException e) {
            event.getChannel().sendMessage("Ошибка выполнения команды, обратитесь к администратору").queue();
            e.printStackTrace();
        }
    }
    
    public void serverStatus(MessageReceivedEvent event) {
        try {
            // Получаем информацию о сервере
            server.get();

            
            // Проверяем статус сервера
            int status = server.getStatus();
            String statusMessage;

            // Устанавливаем сообщение в зависимости от статуса сервера
            switch (status) {
                case ServerStatus.OFFLINE:
                    statusMessage = "Сервер выключен.";
                    break;
                case ServerStatus.ONLINE:
                    statusMessage = "Сервер работает.";
                    break;
                case ServerStatus.STARTING:
                    statusMessage = "Сервер запускается.";
                    break;
                case ServerStatus.STOPPING:
                    statusMessage = "Сервер останавливается.";
                    break;
                case ServerStatus.RESTARTING:
                    statusMessage = "Сервер перезагружается.";
                    break;
                default:
                    statusMessage = "Неизвестный статус сервера: " + status;
            }
            
            statusMessage += "\n Количетсов игроков на сервере: " + server.getPlayerInfo().getCount();
            event.getChannel().sendMessage(statusMessage).queue();
        } catch (APIException e) {
            event.getChannel().sendMessage("Ошибка выполнения команды, обратитесь к администратору").queue();
            e.printStackTrace();
        }
    }
}

