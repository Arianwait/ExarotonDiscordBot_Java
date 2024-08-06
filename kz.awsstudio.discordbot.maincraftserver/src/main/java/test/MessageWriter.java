package test;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageWriter extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Проверяем, является ли автор сообщения ботом
        if (event.getAuthor().isBot()) {
            return;
        }
        ExarotonServer exarotonServer = new ExarotonServer();
        // Получаем текст сообщения
        String message = event.getMessage().getContentDisplay();

        // Проверяем, является ли сообщение командой !start или !end
        if (message.equalsIgnoreCase("!start")) {
        	exarotonServer.serverStart(event);
        } else if (message.equalsIgnoreCase("!end")) {
        	exarotonServer.serverEND(event);
        } else if (message.equalsIgnoreCase("!status")) {
        	exarotonServer.serverStatus(event);
        }  else{
            // Ответ на другие сообщения (опционально)
            event.getChannel().sendMessage("Неизвестная команда.").queue();
        }
    }
}
