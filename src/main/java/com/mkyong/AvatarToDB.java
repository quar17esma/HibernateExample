package com.mkyong;

import com.mkyong.model.avatar.Avatar;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

import java.io.*;

public class AvatarToDB {
    private static final String PATH_IMAGE_TO_DB =
            "C:\\Dell\\Java Projects\\Maven3-Hibernate3.6-Oracle11-Example\\HibernateExample\\src\\main\\" +
                    "resources\\pictures\\avatar.jpg";
    private static final String PATH_IMAGE_FROM_DB =
            "C:\\Dell\\Java Projects\\Maven3-Hibernate3.6-Oracle11-Example\\HibernateExample\\src\\main\\" +
                    "resources\\pictures\\avatarFromDB.jpg";

    public static void main(String[] args) throws IOException {
        System.out.println("Hibernate save image into database");
        HibernateUtil.createSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        int avatarId = saveImageToDB(session);

        getImageFromDB(session, avatarId);

        session.getTransaction().commit();
    }

    private static int saveImageToDB(Session session) throws IOException {

        File file = new File(PATH_IMAGE_TO_DB);
        int fileLength = (int) file.length();
        byte[] bFile = new byte[fileLength];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bFile);
        }

        Avatar avatarToDB = new Avatar();
        avatarToDB.setImage(bFile);

        session.save(avatarToDB);

        return avatarToDB.getAvatarId();
    }

    private static void getImageFromDB(Session session, int avatarId) throws IOException {

        Avatar avatarFromDB = (Avatar) session.get(Avatar.class, avatarId);
        byte[] bAvatar = avatarFromDB.getImage();

        try (FileOutputStream fos = new FileOutputStream(PATH_IMAGE_FROM_DB)) {
            fos.write(bAvatar);
        }
    }
}
