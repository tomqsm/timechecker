package biz.letsweb.fulljar.domain;

/**
 *
 * @author Tomasz
 */
public class Project implements Identifiable {

    private int id;
    private String name;
    private String desc;

    public Project() {
    }

    public Project(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
