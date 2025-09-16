package vn.iotstar.configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * Programmatic SiteMesh 3 configuration to avoid decorator recursion
 * and to control mappings explicitly.
 */
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder
            // Exclude all internal resources under WEB-INF
            .addExcludedPath("/WEB-INF/*")
            .addExcludedPath("/WEB-INF/decorators/*")
            .addExcludedPath("/login*")
            // Map admin first (more specific)
            .addDecoratorPath("/admin/*", "/WEB-INF/decorators/admin.jsp")
            // Default site decorator
            .addDecoratorPath("/*", "/WEB-INF/decorators/web.jsp");
    }
}


