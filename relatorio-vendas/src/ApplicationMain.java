import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApplicationMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("C:\\Users\\Juliane\\Documents\\Cursos\\Vendas");

        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        Map<String, String> files = new HashMap<>();
        while ((key = watchService.take()) != null) {
            watchService.poll(1, TimeUnit.SECONDS);
            for (WatchEvent<?> event : key.pollEvents()) {
                String fileName = event.context().toString();
                if(files.containsKey(fileName) || fileName.contains("~")){
                }else {
                    files.put(fileName, null);
                    System.out.println("Files: " + event.context());
                }
            }
            key.reset();
        }
    }
}
