package pl.kargolek.extension.driver;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import pl.kargolek.extension.util.AnnotationResolver;
import pl.kargolek.util.WebDriverFactory;

/**
 * @author Karol Kuta-Orlowicz
 */
public class WebDriverExtension implements BeforeAllCallback,
        AfterAllCallback,
        BeforeEachCallback,
        AfterEachCallback,
        ParameterResolver {

    private final AnnotationResolver annotationResolver = new AnnotationResolver();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (annotationResolver.getSeleniumWebDriverAnnotation(context)
                .isBeforeAll()) {
            this.initWebDriver();
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (!annotationResolver.getSeleniumWebDriverAnnotation(context)
                .isBeforeAll()) {
            initWebDriver();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (!annotationResolver.getSeleniumWebDriverAnnotation(context)
                .isBeforeAll()) {
            this.tearDownDriver();
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (annotationResolver.getSeleniumWebDriverAnnotation(context).isBeforeAll()) {
            this.tearDownDriver();
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter()
                .getType()
                .equals(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return WebDriverFactory.getRemoteWebDriverInstance();
    }

    private void initWebDriver() {
        WebDriverFactory.getRemoteWebDriverInstance().manage()
                .window()
                .maximize();
    }

    private void tearDownDriver() {
        WebDriverFactory.closeWebDriverInstance();
    }
}
