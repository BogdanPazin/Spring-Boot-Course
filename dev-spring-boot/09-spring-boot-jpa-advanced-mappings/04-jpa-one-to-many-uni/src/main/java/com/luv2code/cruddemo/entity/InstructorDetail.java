package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Instructor_detail")
public class InstructorDetail {
    @Id
    // generated value je tipa identity jer je autoincrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;



    // OVA ANOTACIJA SLUZI DA BI SE OMOGUCIO BIDIRECTION RELATIONSHIP
    // OVO KAZE HIBERNATE-U DA KORISTI instructorDetail ATRIBUT IZ KLASE instructor
    // DA BI ZNAO KAKO DA PRONADJE ODGOVARAJUCI instructor ZA ODGOVARAJUCU KLASU instructorDetail
    // JER CE DA KORISTI JOINCOLLUMN IZ KLASE constructor
    @OneToOne(mappedBy = "instructorDetail",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
                    CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail() {

    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
