# BrowserStack Test Execution - Fixes & Troubleshooting

## Issues Identified

### 1. **SessionNotCreated Error with Empty Capabilities**
**Problem:** The error shows `Capabilities {}` which means the driver is trying to connect without proper setup.

**Root Cause:** 
- The base test class was not properly initializing the UiAutomator2Options before creating the driver
- Missing BrowserStack-specific credentials in capabilities

### 2. **Configuration Mismatch**
**Problem:** Your project has `browserstack.yml` but Java code wasn't reading from it properly.

**Root Cause:**
- Hardcoded credentials fallback was required
- BrowserStack SDK not being invoked correctly

---

## Solutions Implemented

### 1. ✅ Updated `EcommBaseTest.java`

**Changes Made:**
- Moved all capability setup to `@BeforeClass` method
- Added proper error handling and logging
- Ensured `UiAutomator2Options` object is created and populated BEFORE driver instantiation
- Added null checks for environment variables
- Added detailed console output for debugging

**Key Fix:**
```java
@BeforeClass
public void configureAppium() {
    // Setup ALL capabilities here
    options = new UiAutomator2Options();
    options.setApp("bs://c04f78ea6bcf7d40da41abf7f2b26d867e4dad77");
    // ... other capabilities
}

@BeforeMethod
public void setUpDriver() {
    // Use pre-configured options
    driver = new AndroidDriver(appiumServerUrl, options);
}
```

---

## Additional Steps to Fix Issues

### 2. Set Environment Variables (IMPORTANT!)

**Option A: Set in Windows (Permanent)**
```batch
setx BROWSERSTACK_USERNAME "poulamidatta_OM7YbZ"
setx BROWSERSTACK_ACCESS_KEY "Pq2P9jMJ46dqMMQGP1EF"
```
Then restart your IDE/Eclipse.

**Option B: Set in Eclipse IDE**
1. Go to: Run → Run Configurations
2. Select your test configuration
3. Click "Environment" tab
4. Add new variables:
   - Name: `BROWSERSTACK_USERNAME`
   - Value: `poulamidatta_OM7YbZ`
   - Name: `BROWSERSTACK_ACCESS_KEY`
   - Value: `Pq2P9jMJ46dqMMQGP1EF`

**Option C: Set in Maven**
Add to `pom.xml`:
```xml
<properties>
    <browserstack.username>poulamidatta_OM7YbZ</browserstack.username>
    <browserstack.key>Pq2P9jMJ46dqMMQGP1EF</browserstack.key>
</properties>
```

### 3. Verify Network Connectivity

Test connection to BrowserStack server:
```bash
ping app-automate.browserstack.com
```

### 4. Check BrowserStack App Status

Verify the uploaded app is still available:
- Go to: https://app-automate.browserstack.com/
- Login with your credentials
- Check if app `bs://c04f78ea6bcf7d40da41abf7f2b26d867e4dad77` exists

### 5. Update `pom.xml` (Optional but Recommended)

Ensure you have the correct BrowserStack SDK version:
```xml
<dependency>
    <groupId>com.browserstack</groupId>
    <artifactId>browserstack-java-sdk</artifactId>
    <version>1.0.5</version>
    <scope>compile</scope>
</dependency>
```

### 6. Run Maven Clean Build

```bash
mvn clean -DskipTests
mvn compile
```

---

## How to Run Tests

### Using Maven with BrowserStack Profile
```bash
mvn test -PE2ETest -DBROWSERSTACK_USERNAME=poulamidatta_OM7YbZ -DBROWSERSTACK_ACCESS_KEY=Pq2P9jMJ46dqMMQGP1EF
```

### Using Eclipse
1. Right-click test class → Run As → TestNG Test
2. Ensure environment variables are set
3. Check Console output for detailed logs

---

## Verification Checklist

- [ ] Credentials are correctly set (environment variables or hardcoded)
- [ ] Network connection to `app-automate.browserstack.com` works
- [ ] App ID `bs://c04f78ea6bcf7d40da41abf7f2b26d867e4dad77` is valid on BrowserStack
- [ ] Device `Samsung Galaxy S22 Ultra` is available
- [ ] Java version is 11+
- [ ] Appium Java Client version 10.0.0+ is installed
- [ ] No local Appium service running (avoid port conflicts)

---

## Expected Behavior After Fixes

✅ Driver will initialize with proper capabilities
✅ Connection to BrowserStack cloud will succeed
✅ Tests will run on Samsung Galaxy S22 Ultra device
✅ Test results will appear in BrowserStack dashboard
✅ Session details will be logged properly

---

## Debugging Tips

**If still failing:**

1. Check console output for the exact error message
2. Look at BrowserStack dashboard for failed session details
3. Enable debug mode in capabilities (already done)
4. Check logs in: `log/sdk-cli.log`, `log/automation.log`
5. Verify credentials are valid by logging into BrowserStack web portal

