package com.mkyong;

import com.mkyong.dao.AvatarDAO;
import com.mkyong.model.avatar.Avatar;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AppSpring {
    private static final String PATH_IMAGE_TO_DB =
            "C:\\Dell\\Java Projects\\Maven3-Hibernate3.6-Oracle11-Example\\HibernateExample\\src\\main\\" +
                    "resources\\pictures\\avatar.jpg";

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        AvatarDAO avatarDAO = context.getBean(AvatarDAO.class);
        Avatar avatar = getAvatarFromFile();
        avatarDAO.save(avatar);

        context.close();
    }

    private static Avatar getAvatarFromFile() throws IOException {

        File file = new File(PATH_IMAGE_TO_DB);
        int fileLength = (int) file.length();
        byte[] bFile = new byte[fileLength];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bFile);
        }

        Avatar avatar = new Avatar();
        avatar.setImage(bFile);

        return avatar;
    }
}
