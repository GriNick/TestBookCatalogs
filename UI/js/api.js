'use strict';
var app = angular.module('TestApp', ['ui.select', 'ui.bootstrap']);
app.factory('apiService', ['$http', function ($http) {

  var apiURL = 'http://localhost:8080/RESTfulExample/rest';
  return {
    execute: function (type, data) {
      var apiData = { headers: { 'Cache-Control' : 'no-cache' } };

      switch (type) {
        case 'getFullCatalog':
          apiData.url = apiURL + '/catalog/';
          apiData.type = 'GET';
          break;
        case 'getPrivateCatalog':
          apiData.url = apiURL + '/catalog/private';
          apiData.type = 'GET';
          break;
        case 'getPublicCatalog':
          apiData.url = apiURL + '/catalog/private';
          apiData.type = 'GET';
          break;
        case 'getBook':
          apiData.url = apiURL + '/catalog/book/' + data.id;
          apiData.type = 'GET';
          break;
        case 'addBook':
          apiData.url = apiURL + '/catalog/book/';
          apiData.type = 'POST';
          break;
        case 'updateBook':
          apiData.url = apiURL + '/catalog/book/';
          apiData.type = 'PUT';
          break;
        case 'deleteBook':
          apiData.url = apiURL + '/catalog/book/' + data.id;
          apiData.type = 'DELETE';
          break;
        }
        var xhr = $http({
            method: apiData.type,
            url: apiData.url,
            async: true,
            headers: apiData.headers || {},
            //data : JSON.stringify(data),
            data : angular.toJson(data),
            contentType: 'application/json; charset=utf-8',
            dataType : 'json',
        });
        return xhr;
    }
  };
}]);
