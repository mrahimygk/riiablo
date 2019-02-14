package gdx.diablo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import gdx.diablo.BlendMode;
import gdx.diablo.Diablo;
import gdx.diablo.codec.Animation;
import gdx.diablo.codec.DC6;
import gdx.diablo.graphics.PaletteIndexedBatch;
import gdx.diablo.loader.DC6Loader;
import gdx.diablo.widget.Label;
import gdx.diablo.widget.TextButton;

public class MenuScreen extends ScreenAdapter {
  final AssetDescriptor<DC6> TitleScreenDescriptor = new AssetDescriptor<>("data\\global\\ui\\FrontEnd\\TitleScreen.dc6", DC6.class, DC6Loader.DC6Parameters.COMBINE);
  TextureRegion TitleScreen;

  final AssetDescriptor<DC6> D2logoBlackLeftDescriptor  = new AssetDescriptor<>("data\\global\\ui\\FrontEnd\\D2logoBlackLeft.DC6", DC6.class);
  final AssetDescriptor<DC6> D2logoFireLeftDescriptor   = new AssetDescriptor<>("data\\global\\ui\\FrontEnd\\D2logoFireLeft.DC6", DC6.class);
  final AssetDescriptor<DC6> D2logoBlackRightDescriptor = new AssetDescriptor<>("data\\global\\ui\\FrontEnd\\D2logoBlackRight.DC6", DC6.class);
  final AssetDescriptor<DC6> D2logoFireRightDescriptor  = new AssetDescriptor<>("data\\global\\ui\\FrontEnd\\D2logoFireRight.DC6", DC6.class);
  Animation D2logoLeft;
  Animation D2logoRight;

  final AssetDescriptor<DC6>   WideButtonBlankDescriptor = new AssetDescriptor<>("data\\global\\ui\\FrontEnd\\3WideButtonBlank.dc6", DC6.class, DC6Loader.DC6Parameters.COMBINE);
  final AssetDescriptor<Sound> buttonDescriptor = new AssetDescriptor<>("data\\global\\sfx\\cursor\\button.wav", Sound.class);

  final AssetDescriptor<Sound> selectDescriptor = new AssetDescriptor<>("data\\global\\sfx\\cursor\\select.wav", Sound.class);

  private Stage stage;
  private Button btnSinglePlayer;
  private Button btnMultiplayer;
  private Button btnExitDiablo;
  private Label  lbVersion;

  public MenuScreen() {
    this(null, null);
  }

  public MenuScreen(Animation D2logoLeft, Animation D2logoRight) {
    this.D2logoLeft = D2logoLeft;
    this.D2logoRight = D2logoRight;
    Diablo.assets.load(TitleScreenDescriptor);
    Diablo.assets.load(D2logoFireLeftDescriptor);
    Diablo.assets.load(D2logoFireRightDescriptor);
    Diablo.assets.load(D2logoBlackLeftDescriptor);
    Diablo.assets.load(D2logoBlackRightDescriptor);
    Diablo.assets.load(WideButtonBlankDescriptor);
    Diablo.assets.load(buttonDescriptor);
    Diablo.assets.load(selectDescriptor);

    stage = new Stage(Diablo.viewport, Diablo.batch);
  }

  @Override
  public void show() {
    Diablo.assets.finishLoadingAsset(TitleScreenDescriptor);
    TitleScreen = Diablo.assets.get(TitleScreenDescriptor).getTexture();

    if (D2logoLeft == null || D2logoRight == null) {
      Diablo.music.enqueue("data/global/music/Act1/tristram.wav");
    }

    if (D2logoLeft == null) {
      Diablo.assets.finishLoadingAsset(D2logoBlackLeftDescriptor);
      Diablo.assets.finishLoadingAsset(D2logoFireLeftDescriptor);
      D2logoLeft = Animation.builder()
          .layer(Diablo.assets.get(D2logoBlackLeftDescriptor))
          .layer(Diablo.assets.get(D2logoFireLeftDescriptor), BlendMode.LUMINOSITY)
          .build();
    }

    if (D2logoRight == null) {
      Diablo.assets.finishLoadingAsset(D2logoBlackRightDescriptor);
      Diablo.assets.finishLoadingAsset(D2logoFireRightDescriptor);
      D2logoRight = Animation.builder()
          .layer(Diablo.assets.get(D2logoBlackRightDescriptor))
          .layer(Diablo.assets.get(D2logoFireRightDescriptor), BlendMode.LUMINOSITY)
          .build();
    }

    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle() {{
        Diablo.assets.finishLoadingAsset(WideButtonBlankDescriptor);
        DC6 WideButtonBlank = Diablo.assets.get(WideButtonBlankDescriptor);
        up   = new TextureRegionDrawable(WideButtonBlank.getTexture(0));
        down = new TextureRegionDrawable(WideButtonBlank.getTexture(1));
        font = Diablo.fonts.fontexocet10;
    }};
    ClickListener clickListener = new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        Actor actor = event.getListenerActor();
        if (actor == btnSinglePlayer) {
          Diablo.client.pushScreen(new SelectCharacterScreen());
        } else if (actor == btnMultiplayer) {
          Diablo.client.pushScreen(new MultiplayerScreen(D2logoLeft, D2logoRight));
        } else if (actor == btnExitDiablo) {
          Gdx.app.exit();
        }
      }
    };
    btnSinglePlayer = new TextButton(5106, style);
    btnSinglePlayer.addListener(clickListener);
    btnMultiplayer = new TextButton(5107, style);
    btnMultiplayer.addListener(clickListener);
    btnExitDiablo = new TextButton(5109, style);
    btnExitDiablo.addListener(clickListener);

    Table panel = new Table() {{
      add(btnSinglePlayer).space(8).row();
      add(btnMultiplayer).space(8).row();
      add(btnExitDiablo).space(8).row();
    }};
    panel.setX(stage.getWidth() / 2);
    panel.setY(stage.getHeight() * 0.40f);
    stage.addActor(panel);

    lbVersion = new Label(Diablo.bundle.get("version"), Diablo.fonts.font16);
    lbVersion.setPosition(20, 20);
    stage.addActor(lbVersion);

    Diablo.input.addProcessor(stage);
  }

  @Override
  public void hide() {
    Diablo.input.removeProcessor(stage);
  }

  @Override
  public void dispose() {
    Diablo.assets.unload(TitleScreenDescriptor.fileName);
    Diablo.assets.unload(D2logoFireLeftDescriptor.fileName);
    Diablo.assets.unload(D2logoFireRightDescriptor.fileName);
    Diablo.assets.unload(D2logoBlackLeftDescriptor.fileName);
    Diablo.assets.unload(D2logoBlackRightDescriptor.fileName);
    Diablo.assets.unload(WideButtonBlankDescriptor.fileName);
    Diablo.assets.unload(buttonDescriptor.fileName);
    Diablo.assets.unload(selectDescriptor.fileName);
  }

  @Override
  public void render(float delta) {
    PaletteIndexedBatch b = Diablo.batch;
    b.begin(Diablo.palettes.units);
    b.draw(TitleScreen, Diablo.VIRTUAL_WIDTH_CENTER - (TitleScreen.getRegionWidth() / 2), 0);

    int x = Diablo.VIRTUAL_WIDTH_CENTER;
    float y = (Diablo.VIRTUAL_HEIGHT * 0.75f);
    D2logoLeft.act(delta);
    D2logoLeft.draw(b, x, y);
    D2logoRight.act(delta);
    D2logoRight.draw(b, x, y);

    b.end();

    stage.act(delta);
    stage.draw();
  }
}