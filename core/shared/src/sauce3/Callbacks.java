package sauce3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class Callbacks {
    private Sauce3VM vm;
    private LuaValue root;
    private boolean enabled;

    public Callbacks(Sauce3VM vm) {
        this.vm = vm;
    }

    public void enable() {
        enabled = true;
        root = vm.lua.get("sauce3");
    }

    public void load() {
        run_callback("load");
    }

    public void run() {
        run_callback("run");
    }

    public void resize(int width, int height) {
    run_callback("resize", width, height);
  }

  public void visible(boolean isVisible) {
    run_callback("visible", isVisible);
  }

  public void quit() {
    run_callback("_quit");
  }

  public void keypressed(int keycode) {
    run_callback("_key_pressed", keycode);
  }

  public void keyreleased(int keycode) {
    run_callback("_key_released", keycode);
  }

  public void textinput(String character) {
    run_callback("text_input", character);
  }

  public void touchpressed(int x, int y, int pointer) {
    run_callback("touch_pressed", x, y, pointer);
  }

  public void mousepressed(int x, int y, int buttoncode) {
    run_callback("_mouse_pressed", x, y, buttoncode);
  }

  public void touchreleased(int x, int y, int pointer) {
    run_callback("touch_released", x, y, pointer);
  }

  public void mousereleased(int x, int y, int buttoncode) {
    run_callback("_mouse_released", x, y, buttoncode);
  }

  public void touchmoved(int x, int y, int pointer) {
    run_callback("touch_moved", x, y, pointer);
  }

  public void mousemoved(int x, int y) {
    run_callback("mouse_moved", x, y);
  }

  public void mousescrolled(int amount) {
    run_callback("mouse_scrolled", amount);
  }

  private void run_callback(String key, Object... args) {
    if (!enabled || vm.lua == null) {
      return;
    }

    LuaValue callback = root.get(key);

    if (!callback.isfunction()) {
      return;
    }

    switch (args.length) {
      case 0:
        callback.call();
        break;
      case 1:
        callback.call(CoerceJavaToLua.coerce(args[0]));
        break;
      case 2:
        callback.call(CoerceJavaToLua.coerce(args[0]), CoerceJavaToLua.coerce(args[1]));
        break;
      case 3:
        callback.call(CoerceJavaToLua.coerce(args[0]), CoerceJavaToLua.coerce(args[1]), CoerceJavaToLua.coerce(args[2]));
        break;
    }
}
}
