package pro.antonvmax.xspringrestjpa.hotel;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
class Hotel {
    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HotelIdGenerator")
    @GenericGenerator(name = "HotelIdGenerator",
            strategy = "pro.antonvmax.xspringrestjpa.hotel.HotelIdSequenceGenerator",
            parameters = {
                    @Parameter(name = "sequence", value = "hotel_id_sequence")
            })
    @NotNull
    @Size(min = 1, max = 12)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /***
     * name
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /***
     * catid
     */
    @Size(min = 1, max = 12)
    private String catid;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    /***
     * point
     */
    @Column
    Double pointP1;
    @Column
    Double pointP2;

    public Double[] getPoint() {
        return new Double[]{pointP1, pointP2};
    }

    public void setPoint(Double[] point) {
        if (point.length != 2) {
            throw new RuntimeException("point array must contain exactly 2 values");
        }
        pointP1 = point[0];
        pointP2 = point[1];
    }

    /***
     * addr
     */
    @Size(min = 1, max = 255)
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    /***
     * img
     */
    @Size(min = 1, max = 12)
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    /***
     * site
     */
    @Embedded
    public HotelSite site;

    public HotelSite getSite() {
        return site;
    }

    public void setSite(HotelSite site) {
        this.site = site;
    }

    /***
     * services
     */
    @Column
    private String services;

    enum ServicesEnum {
        restaurant, transfer, bar, fitness, beach
    }

    public String[] getServices() {
        if (services == null) {
            return null;
        }
        return services.split(",");
    }

    public void setServices(String[] services) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < services.length; i++) {
            // проверим, что содержатся значения из перечисленных
            try {
                ServicesEnum servicesEnum = ServicesEnum.valueOf(services[i]);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("service '" + services[i] + "' is not allowed string");
            }
            sb.append(services[i]).append(",");
        }
        this.services = sb.toString();
        // cut last ','
        this.services = this.services.substring(0, this.services.length() - 1);
        System.out.println("this.services = " + this.services);
    }

    public Hotel() {
    }

    public Hotel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", catid='" + catid + '\'' +
                ", pointP1=" + pointP1 +
                ", pointP2=" + pointP2 +
                ", addr='" + addr + '\'' +
                ", img='" + img + '\'' +
                ", site=" + site +
                ", services='" + services + '\'' +
                '}';
    }
}

class HotelSite {
    private String label;
    private String url;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HotelSite{" +
                "label='" + label + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
