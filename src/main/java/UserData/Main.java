package UserData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            adding();
            if (r.readLine().equals("stop")) {
                break;
            }
        }
    }

    public static void adding() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User user = new User();
        System.out.println("Введите имя");
        String name = reader.readLine();
        System.out.println("Введите пароль");
        String password = reader.readLine();
        user.setName(name);
        user.setPassword(password);
        users.add(user);
        adder(users);
    }

    public static void adder(List<User> users) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("user.dat"));
        for (int i = 0; i < users.size(); i++) {
            writer.write(users.get(i).getName() + " " + users.get(i).getPassword());
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
