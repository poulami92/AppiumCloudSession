# BROWSERSTACK TEST EXECUTION - COMPLETE FIX GUIDE

## Problem Summary
Your tests were failing with: **"SessionNotCreated: Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure."** with empty capabilities `{}`.

---

## ROOT CAUSES IDENTIFIED

### 1. **Empty Capabilities Issue**
- The `UiAutomator2Options` were not being properly initialized before creating the driver
- Capabilities weren't being passed correctly to the AndroidDriver constructor

### 2. **Configuration Mismatch**
- Code was trying to use local Appium server (127.0.0.1:4723) instead of BrowserStack cloud
- BrowserStack credentials were not properly embedded in the driver setup

### 3. **Execution Flow Problem**
- `@BeforeClass` and `@BeforeMethod` were not properly sequenced
- Options object needed to be fully configured in `@BeforeClass` before use in `@BeforeMethod`

---

## FIXES IMPLEMENTED ✅

### File: `EcommBaseTest.java` (UPDATED)

**Key Changes:**
1. ✅ Moved all capability setup to `@BeforeClass` method
2. ✅ Added BrowserStack credentials handling (environment variables + fallback)
3. ✅ Removed local Appium service startup
4. ✅ Added proper error handling and logging
5. ✅ Ensured `UiAutomator2Options` is fully populated BEFORE driver creation

**Configuration Details:**
```
- Server URL: https://app-automate.browserstack.com/wd/hub
- Device: Samsung Galaxy S22 Ultra
- Platform: Android 12.0
- Automation: UiAutomator2
- App ID: bs://c04f78ea6bcf7d40da41abf7f2b26d867e4dad77
```

---

## NEXT STEPS - SET UP ENVIRONMENT VARIABLES

### **CRITICAL: Set BrowserStack Credentials in Windows**

You MUST set environment variables for the credentials. Choose ONE method:

#### **Method 1: Windows Environment Variables (PERMANENT - Recommended)**
```batch
# Open Command Prompt as Administrator and run:
setx BROWSERSTACK_USERNAME "poulamidatta_OM7YbZ"
setx BROWSERSTACK_ACCESS_KEY "Pq2P9jMJ46dqMMQGP1EF"
```
**Then restart Eclipse/IDE for changes to take effect.**

#### **Method 2: Eclipse Run Configuration (TEMPORARY)**
1. Open Eclipse
2. Go to: **Run → Run Configurations**
3. Select your TestNG configuration (or create new one)
4. Click **Environment** tab
5. Click **New** and add:
   - **Name:** `BROWSERSTACK_USERNAME`
   - **Value:** `poulamidatta_OM7YbZ`
6. Click **New** again and add:
   - **Name:** `BROWSERSTACK_ACCESS_KEY`
   - **Value:** `Pq2P9jMJ46dqMMQGP1EF`
7. Click **Apply** and **Close**

#### **Method 3: Maven Command Line (PER-RUN)**
```bash
mvn test -PE2ETest -DBROWSERSTACK_USERNAME=poulamidatta_OM7YbZ -DBROWSERSTACK_ACCESS_KEY=Pq2P9jMJ46dqMMQGP1EF
```

---

## HOW TO RUN TESTS

### **Option 1: Using Eclipse UI**
1. Right-click on test class: `E2EEcommTests_POM`
2. Select: **Run As → TestNG Test**
3. Monitor Console for output

### **Option 2: Using Maven**
```bash
cd c:\Users\MSUSERSL123\eclipse-workspace\AppiumSession_Cloud
mvn clean test -PE2ETest
```

### **Option 3: Using Maven with Maven Wrapper**
```bash
mvn clean compile
mvn test -PE2ETest -DskipTests=false
```

---

## VERIFICATION CHECKLIST

Before running tests, verify:

- [ ] **Credentials Set:** Environment variables `BROWSERSTACK_USERNAME` and `BROWSERSTACK_ACCESS_KEY` are set
- [ ] **Network:** Can access https://app-automate.browserstack.com (check: `ping app-automate.browserstack.com`)
- [ ] **App Valid:** App ID `bs://c04f78ea6bcf7d40da41abf7f2b26d867e4dad77` exists in your BrowserStack account
- [ ] **Device Available:** Samsung Galaxy S22 Ultra with Android 12.0 is in your plan
- [ ] **No Local Service:** No local Appium server running on port 4723
- [ ] **Java Version:** Java 11+ installed (`java -version`)
- [ ] **Maven:** Maven installed and working (`mvn -version`)
- [ ] **Dependencies:** Run `mvn dependency:resolve` to verify all dependencies

---

## EXPECTED TEST FLOW

### What Should Happen Now:

```
1. @BeforeClass - configureAppium()
   └─ Creates UiAutomator2Options
   └─ Loads BrowserStack credentials
   └─ Sets all capabilities

2. @BeforeMethod - setUpDriver()
   └─ Connects to BrowserStack cloud (https://app-automate.browserstack.com/wd/hub)
   └─ Creates AndroidDriver with pre-configured options
   └─ Initializes FormPage

3. @Test - PurchaseProduct() or validateToastMessage()
   └─ Runs actual test scenarios

4. @AfterMethod - quitDriver()
   └─ Quits driver session

5. @AfterClass - tearDown()
   └─ Cleanup
```

---

## TROUBLESHOOTING IF TESTS STILL FAIL

### **Error: "SessionNotCreated"**
- ✅ Check credentials are set (print in console: `System.getenv("BROWSERSTACK_USERNAME")`)
- ✅ Verify app ID exists on BrowserStack
- ✅ Check network connectivity to `app-automate.browserstack.com`

### **Error: "Could not start a new session"**
- ✅ Ensure no local Appium service is running
- ✅ Check if device is available in your BrowserStack plan
- ✅ Verify internet connection is stable

### **Error: "Empty Capabilities {}"**
- ✅ Check that `@BeforeClass` completes before `@BeforeMethod` runs
- ✅ Verify `UiAutomator2Options` object is being created

### **Debugging Tips:**
1. Check console output for all `System.out.println()` statements
2. Look for error stack traces starting with "Failed to initialize driver:"
3. Visit BrowserStack Dashboard: https://app-automate.browserstack.com/
4. Check Session Logs for detailed failure reasons
5. Enable screenshot: `options.setCapability("browserstack.debug", true);` (already enabled)

---

## LOG FILES TO CHECK

If tests fail, check these logs:

```
c:\Users\MSUSERSL123\eclipse-workspace\AppiumSession_Cloud\
├── log/sdk-cli.log          ← BrowserStack SDK logs
├── log/automation.log       ← Automation logs
├── logs/                    ← Additional logs
└── test-output/             ← TestNG output
```

---

## FINAL CHECKLIST BEFORE RUNNING

- [ ] File `EcommBaseTest.java` has been updated with fixes
- [ ] Environment variables are set with YOUR credentials
- [ ] testNG XML file points to correct test class
- [ ] Maven profile `E2ETest` is properly configured in pom.xml
- [ ] No syntax/compilation errors in IDE
- [ ] Dependencies are downloaded (`mvn dependency:resolve`)

---

## SUCCESS INDICATORS

When tests run successfully, you should see:

```
Console Output:
✓ "Configuring Appium with BrowserStack credentials..."
✓ "Appium options configured successfully"
✓ "Attempting to create AndroidDriver with URL: https://app-automate.browserstack.com/wd/hub"
✓ "AndroidDriver created successfully!"
✓ Test methods execute
✓ "Driver quit successfully"
```

AND in BrowserStack Dashboard:
- Session appears with "PASSED" or "FAILED" status
- Screenshots are captured
- Test logs are available

---

**Now proceed with Method 1 (Windows Environment Variables) and then run your tests!**

