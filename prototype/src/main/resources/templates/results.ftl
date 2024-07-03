<!DOCTYPE html>
<html></html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link th:href="@{/styling/styles.css}" rel="stylesheet" type="text/css">
        <title>Results Page</title>
    </head>
    
    <body>
        <div>
            <h1>Results Page</h1>
        </div>


        <#list productList as prods>
            <div>
                <div>
                    <img th:src="${prods.imgLink}" height="150px" width="250px"/>
                    
                    <b><p>${prods.name}</p></b>
                    <p>${prods.price}</p>
                </div>
            </div>
        </#list>
    </body>
</html>