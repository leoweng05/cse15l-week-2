import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> arr = new ArrayList<>();

    public String handleRequest(URI url) {
        String link = url.getPath();
        if (link.equals("/")) {
            return "Welcome!";
        } 
        else {
            if (!items[0].equals("s")){
                return "404 Not Found";
            }
            if (link.contains("/add")) {
                arr.add(items[1]);
                return String.format("%s added to the list!", items[1]);
            } 
            else if (link.contains("/search")){
                String list = "";
                int count = 1;
                for (String str: arr){
                    if (str.contains(items[1])){
                        list += count + ". " + str + "\n";
                        count++;
                    }
                }
                return list;
            }
        return "404 Not Found!";
    }
}
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
