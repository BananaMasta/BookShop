package UserData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User {

    private String name;
    private String password;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<User> userSorting(List<User> users) {
        List<User> sorted = new ArrayList<>(users);
        sorted.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return sorted;
    }

    public String toString() {
        return getName() + " " + getPassword();
    }
}
