package imageApp.Entity;

public class Image {
    private int id;
    private String title;

    public Image(int id, String name) {
        this.id = id;
        this.title = name;
    }
    public Image(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

}
