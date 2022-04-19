package settings;

import org.testng.annotations.Test;

public class SettingsTest {
    //TC ID TRE110 Profile and visibility section checking.
    @Test (groups={"critical_path"})
    public void profileAndVisibilityTest(){}

    //TC ID TRE111 Activity section checking.
    @Test
    public void activityTest(){}

    //TC ID TRE112 Cards section checking.
    @Test
    public void cardsTest(){}

    //TC ID TRE113 Change language section checking.
    @Test (groups={"critical_path"})
    public void changeLanguageTest(){}

    //TC ID TRE114 Change notification email frequency checking.
    @Test (groups={"critical_path"})
    public void changeNotificationEmailFrequencyTest(){}

    //TC ID TRE115 Allow desktop notifications checking.
    @Test
    public void allowDesktopNotificationsTest(){}

    //TC ID TRE116 Enable/disable suggestions checking.
    @Test
    public void enableDisableSuggestionsTest(){}

    //TC ID TRE117 Opt out of Marketing emails checking.
    @Test
    public void optOutOfMarketingEmailsTest(){}

    //TC ID TRE118 Enable color-blind friendly mode checking.
    @Test
    public void enableColorBlindFriendlyModeTest(){}

    //TC ID TRE119 Testing the adding and deleting the apps to/from Trello.
    @Test
    public void applicationsTest(){}

    //TC ID TRE120 Testing the turning out the session.
    @Test
    public void manageYourRecentDevicesTest(){}

    //TC ID TRE121 Testing the 2-Factor Authentication.
    @Test
    public void configureTwoFAAuthenticationTest(){}

    //TC ID TRE122 Testing the JSON is correct.
    @Test
    public void downloadPersonalDataTest(){}

    //TC ID TRE123 Testing the Manage or Delete account function.
    @Test (groups={"critical_path"})
    public void manageOrDeleteAccountTest(){}
}
