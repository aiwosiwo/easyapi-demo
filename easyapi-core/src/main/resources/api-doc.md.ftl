<#list controllerNodes as controller>
# ${controller.description}
<#list controller.requestNodes as reqNode>
## <#if reqNode.deprecated>~~${(reqNode.description)!''}~~<#else>${(reqNode.description)!''}</#if>

*<#if reqNode.author??>${i18n.getMessage('author')}: ${reqNode.author}</#if>*

**${i18n.getMessage('requestUrl')}**

${reqNode.url} <#list reqNode.method as method>`${method}` </#list>

<#if reqNode.paramNodes?size != 0>
    <#assign requestJsonBody = ''/>
    <#list reqNode.paramNodes as paramNode>
        <#if paramNode.jsonBody>
            <#assign requestJsonBody = paramNode.description/>
        </#if>
    </#list>
    <#if requestJsonBody == '' || (requestJsonBody != '' && reqNode.paramNodes?size gt 1)>
**${i18n.getMessage('requestParameters')}**

${i18n.getMessage('parameterName')!''}|${i18n.getMessage('parameterType')!''}|${i18n.getMessage('parameterNeed')}|${i18n.getMessage('description')!''}
--:|:--:|:--:|:--
        <#list reqNode.paramNodes as paramNode>
            <#if !(paramNode.jsonBody)>
${paramNode.name}|${paramNode.type!''}|${paramNode.required?string(i18n.getMessage('yes'),i18n.getMessage('no'))}|${(paramNode.description)!''}
            </#if>
        </#list>
    </#if>
    <#if requestJsonBody != ''>
**${i18n.getMessage('requestBody')}**

```json
${requestJsonBody}
```
    </#if>
</#if>

<#if reqNode.responseNode??>
**${i18n.getMessage('responseResult')}**

```json
${reqNode.responseNode.toJsonApi()}
```
</#if>
</#list>
</#list>
<br/>


