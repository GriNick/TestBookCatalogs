
var app = angular.module('TestApp');

app.controller('MyCtrl', ['$scope', 'apiService', '$uibModal', '$filter', function($scope, apiService, $uibModal, $filter) {
     var $ctrl = this;
/*     $scope.dateOptions = {
         dateDisabled: false,
         formatYear: 'yy',
         maxDate: new Date(2020, 5, 22),
         minDate: new Date(1800, 1, 1),
         startingDay: 1
       };*/


     $scope.changeType =  function(book) {
          book.type = (book.type == 1 ? 2 : 1);
          apiService.execute('updateBook', book)
          .then(function(result) {
               if (result.status == 200 || result.status == 204) {
                    if (result.data.success === 0 ) {
                         console.log(result.data.message);
                         book.type = (book.type == 1 ? 2 : 1);
                    }
               } else {
                    console.log('Request failed.');
                    book.type = (book.type == 1 ? 2 : 1);
               }

          }, function(err) {
               console.log(err);
               book.type = (book.type == 1 ? 2 : 1);
          });
     };

     $scope.deleteBook = function(book, id) {
      apiService.execute('deleteBook', book)
      .then(function(result) {
        if (result.status == 200 || result.status == 204) {
              if (result.data.success === 0 ) {
                   console.log(result.data.message);
              } else {
                $scope.catalog.splice(id,1);
              }
        } else {
                  console.log('Request failed.');
        }
      }, function(err) {
        console.log(err);
      });
     };

     $ctrl.openAddDialog = function () {
      // var parentElem = parentSelector ? angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
      var modalInstance = $uibModal.open({
           animation: $ctrl.animationsEnabled,
           ariaLabelledBy: 'modal-title',
           ariaDescribedBy: 'modal-body',
           templateUrl: 'modalEditBook.html',
           controller: 'ModalInstanceCtrl',
           controllerAs: '$ctrl',
           resolve: {
             book: function () {
               return null;
             }
           }
    });

    modalInstance.result.then(function (created) {
          var newBook = created.new;
          var data = {
               name: newBook.name,
               author: newBook.author,
               type: newBook.catalog.id,
               dt: $filter('date')(newBook.dt,'yyyy-MM-dd')
          };

          apiService.execute('addBook', data)
          .then(function(result) {
                    console.log(result);
                    if (result.status == 201) {
                         data.id = result.data && result.data.uuid;
                         $scope.catalog.push(data);
                    }
               }, function(err) {
                    console.log(err);
               });
          });
     };

      $scope.openEditDialog = function (book) {
      // var parentElem = parentSelector ? angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
      var modalInstance = $uibModal.open({
           animation: $ctrl.animationsEnabled,
           ariaLabelledBy: 'modal-title',
           ariaDescribedBy: 'modal-body',
           templateUrl: 'modalEditBook.html',
           controller: 'ModalInstanceCtrl',
           controllerAs: '$ctrl',
           resolve: {
             book:  function () {
               return book;
             }
           }
    });

    modalInstance.result.then(function (edited) {
          var newBook = edited.new;
          var oldBook = edited.old;
          var data = {
               id: newBook.id,
               name: newBook.name,
               author: newBook.author,
               type: newBook.catalog.id,
               dt: $filter('date')(newBook.dt,'yyyy-MM-dd')
          };

          apiService.execute('updateBook', data)
          .then(function(result) {
               if (result.status == 200 || result.status == 204) {
                    if (result.data.success === 0 ) {
                         console.log(result.data.message);

                    } else {
                      if (oldBook) {
                        oldBook.id = newBook.id;
                        oldBook.name = newBook.name;
                        oldBook.author = newBook.author;
                        oldBook.type = newBook.catalog && newBook.catalog.id;
                        oldBook.dt = $filter('date')(newBook.dt,'dd-MM-yyyy') ;
                      }
                    }
               } else {
                    console.log('Request failed.');
               }

          }, function(err) {
               console.log(err);
          });
     })
      .catch(function(err) {
        console.log(err);
      });
    };



     apiService.execute('getFullCatalog')
     .then(function(result) {
     	console.log(result);
          if (result.status == 200)  {
               $scope.catalog = result.data;
          }
     }, function(err) {
     	console.log(err);
     });

 }]);


app.controller('ModalInstanceCtrl', ['$scope', '$uibModalInstance', '$filter', 'book',  function ($scope, $uibModalInstance, $filter, book) {
  $ctrl = this;


  $scope.datePicker = {
     opened: false
  };

  if (book) {
    $scope.newBook = {
       id: book.id,
       name: book.name,
       author: book.author,
       catalog: {
        id: book.type,
        type: (book.type == 2 ? 'Public' :'Private')
       },
       dt: new Date(book.dt)
    };
  } else {
    $scope.newBook =   {
       dt: new Date(2000,0,1),
       catalog :  {
            type: 'Public',
            id: 2
       }
    };
  }

  $scope.catalogTypes =[{type: "Private", id: 1}, {type: "Public", id: 2}];
  $scope.setType = function (type) {
   $scope.book.type = type;
  };
  $scope.openDatePicker = function() {
    $scope.datePicker.opened = true;
  };
  $ctrl.ok = function () {
    $uibModalInstance.close({ new: $scope.newBook, old: book });
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);