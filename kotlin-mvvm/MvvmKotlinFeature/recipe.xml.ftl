<?xml version="1.0"?>
<recipe>
    <instantiate from="src/app_package/MvvmFragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}Fragment.java" />
    <instantiate from="src/app_package/ViewModel.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${subpackageName}/${className}ViewModel.java" />
    <instantiate from="src/app_package/layout.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${subpackageName}_fragment.xml" />

    <open file="${srcOut}/${subpackageName}/${className}Fragment.java"/>
</recipe>
