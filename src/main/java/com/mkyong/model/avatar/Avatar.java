package com.mkyong.model.avatar;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "avatar")
public class Avatar implements Serializable {

    private Integer avatarId;
    private byte[] image;

    public Avatar() {
    }

    public Avatar(byte[] image) {
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVATAR_ID", nullable = false, unique = true)
    public Integer getAvatarId() {
        return this.avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    @Column(name = "IMAGE", nullable = false)
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
