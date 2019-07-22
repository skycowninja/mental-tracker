package tracker;


import static com.codename1.ui.CN.*;

import tracker.Pages.MainPage;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose
 * of building native mobile applications using Java.
 */
public class CoreApp implements LocalNotificationCallback{

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

    }
    
    public void start() {

        if(current != null){
            current.show();
            return;
        }
        MainPage main = new MainPage(false);
        main.setSidePanel();
        main.show();

        //login.show();

        //main.Show();

    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

    @Override
    public void localNotificationReceived(String notificationId) {

        //Testing purposes
        System.out.println("Recieved local notification" + notificationId);

    }
}