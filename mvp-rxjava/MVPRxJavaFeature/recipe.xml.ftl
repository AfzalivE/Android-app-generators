<?xml version="1.0"?>
<recipe>

    <merge from="src/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    <instantiate from="src/app_package/Contract.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}Contract.java" />
    <instantiate from="src/app_package/MvpActivity.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}Activity.java" />
    <instantiate from="src/app_package/MvpFragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}Fragment.java" />
    <instantiate from="src/app_package/Presenter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}Presenter.java" />
    <instantiate from="src/app_package/PresenterFactory.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}PresenterFactory.java" />
    <instantiate from="src/app_package/layout.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${subpackageName}_fragment.xml" />
 
    <open file="${srcOut}/${subpackageName}/${className}Presenter.java"/>
</recipe>