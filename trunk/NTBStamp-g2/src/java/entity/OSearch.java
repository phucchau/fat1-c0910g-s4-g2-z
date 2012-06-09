/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author TRUONG
 */
public class OSearch {
    private int id;
    private String type;
    private String title;
    private String image;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
     public String getSubDescription(){
        Removetag rm = new Removetag();
        String subdes = rm.StripAllTags(description);
        if (subdes.length() >= 200) {
            return subdes.substring(0,200)+" ...";
        }else{
            return subdes;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
