package letterlinkodessey;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterManage {
    private ImageView characterImageView;

    public CharacterManage() {
        characterImageView = new ImageView();
        characterImageView.setFitWidth(300);
        characterImageView.setPreserveRatio(true);
        characterImageView.setTranslateY(50); // Position adjustment
    }

    public void setCharacterImage(String path) {
        if (path != null && !path.isEmpty()) {
            Image image = new Image("file:" + path);
            characterImageView.setImage(image);
        }
    }

    public ImageView getCharacterImageView() {
        return characterImageView;
    }
}
