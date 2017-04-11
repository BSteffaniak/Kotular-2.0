if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Kotular'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Kotular'.");
}
var Kotular = function (_, Kotlin) {
  'use strict';
  function main$lambda() {
    return new TodoStorage();
  }
  function main$lambda_0(x) {
    return todoFocus(x);
  }
  function main$lambda_1(a, b, c, d) {
    TodoCtrl(a, b, c, d);
  }
  function main(args) {
    var todomvc = angular.module('todomvc', array());
    todomvc.factory('todoStorage', array(main$lambda));
    todomvc.directive('todoFocus', array('$timeout', main$lambda_0));
    todomvc.controller('TodoCtrl', array('$scope', '$location', 'todoStorage', 'filterFilter', main$lambda_1));
  }
  function TodoCtrl$lambda(closure$filterFilter, closure$scope) {
    return function (it) {
      closure$scope.remainingCount = closure$filterFilter.invoke(closure$scope.todos, false).length;
      closure$scope.completedCount = closure$scope.todos.length - closure$scope.remainingCount | 0;
      closure$scope.completedCount = closure$scope.todos.length - closure$scope.remainingCount | 0;
      closure$scope.allChecked = closure$scope.remainingCount === 0;
    };
  }
  function TodoCtrl$lambda_0(closure$scope) {
    return function (path) {
      var tmp$, tmp$_0;
      tmp$_0 = closure$scope;
      if (Kotlin.equals(path, '/active'))
        tmp$ = false;
      else if (Kotlin.equals(path, '/completed'))
        tmp$ = true;
      else
        tmp$ = null;
      tmp$_0.statusFilter = tmp$;
    };
  }
  function TodoCtrl$lambda_1(closure$scope) {
    return function () {
      if (closure$scope.newTodo.length > 0) {
        var todo = new Todo();
        todo.title = closure$scope.newTodo;
        todo.completed = false;
        scope.todos.push(todo);
        closure$scope.newTodo = '';
      }
    };
  }
  function TodoCtrl$lambda_2(closure$scope) {
    return function (todo) {
      closure$scope.editedTodo = todo;
    };
  }
  function TodoCtrl$lambda_3(closure$scope) {
    return function (todo) {
      closure$scope.editedTodo = null;
      if (todo.title != null) {
        closure$scope.removeTodo(todo);
      }
    };
  }
  function TodoCtrl$lambda_4(todo) {
    scope.todos.splice(scope.todos.indexOf(todo), 1);
  }
  function TodoCtrl$lambda_5() {
    scope.todos = scope.todos.filter(function (it) {
      !it.completed;
    });
  }
  function TodoCtrl$lambda_6(closure$scope) {
    return function (completed) {
      var $receiver = closure$scope.todos;
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
        var element = $receiver[tmp$];
        element.completed = completed;
      }
    };
  }
  function TodoCtrl(scope, location, todoStorage, filterFilter) {
    scope.todos = todoStorage.get();
    scope.newTodo = '';
    scope.editedTodo = null;
    scope.$watch('todos', TodoCtrl$lambda(filterFilter, scope), true);
    if (Kotlin.equals(location.path(), '')) {
      location.path('/');
    }
    scope.location = location;
    scope.$watch('location.path()', TodoCtrl$lambda_0(scope));
    scope.addTodo = TodoCtrl$lambda_1(scope);
    scope.editTodo = TodoCtrl$lambda_2(scope);
    scope.doneEditing = TodoCtrl$lambda_3(scope);
    scope.removeTodo = TodoCtrl$lambda_4;
    scope.clearCompletedTodos = TodoCtrl$lambda_5;
    scope.markAll = TodoCtrl$lambda_6(scope);
  }
  function todoFocus$lambda$lambda(newVal) {
    var tmp$;
    if (typeof (tmp$ = newVal) === 'boolean' ? tmp$ : Kotlin.throwCCE()) {
      timeout(function () {
        elem[0].focus();
      }, 0, false);
    }
  }
  function todoFocus$lambda(scope, elem, _attrs) {
    var tmp$;
    var attrs = Kotlin.isType(tmp$ = _attrs, Object) ? tmp$ : Kotlin.throwCCE();
    scope.$watch(attrs.todoFocus, todoFocus$lambda$lambda);
  }
  function todoFocus(timeout) {
    var directive = new Directive();
    directive.link = todoFocus$lambda;
    return directive;
  }
  function todoBlur$lambda(scope, elem, _attrs) {
    var tmp$;
    var attrs = Kotlin.isType(tmp$ = _attrs, Object) ? tmp$ : Kotlin.throwCCE();
    scope.$apply(attrs.todoBlur);
  }
  function todoBlur() {
    var directive = new Directive();
    directive.link = todoBlur$lambda;
    return directive;
  }
  function TodoStorage() {
    this.STORAGE_ID_0 = 'TODOS-angularjs';
  }
  TodoStorage.prototype.get = function () {
    var tmp$;
    var data = localStorage.getItem(this.STORAGE_ID_0);
    if (data == null) {
      data = '[]';
    }
    return Array.isArray(tmp$ = JSON.parse(data != null ? data : Kotlin.throwNPE())) ? tmp$ : Kotlin.throwCCE();
  };
  TodoStorage.prototype.put_161vx0$ = function (data) {
    localStorage.setItem(this.STORAGE_ID_0, JSON.stringify(data));
  };
  TodoStorage.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'TodoStorage',
    interfaces: []
  };
  var package$com = _.com || (_.com = {});
  var package$campspot = package$com.campspot || (package$com.campspot = {});
  var package$kotular = package$campspot.kotular || (package$campspot.kotular = {});
  package$kotular.main_kand9s$ = main;
  package$kotular.TodoCtrl_dlr1tu$ = TodoCtrl;
  package$kotular.todoFocus_kg20jj$ = todoFocus;
  package$kotular.todoBlur = todoBlur;
  package$kotular.TodoStorage = TodoStorage;
  Kotlin.defineModule('Kotular', _);
  main([]);
  return _;
}(typeof Kotular === 'undefined' ? {} : Kotular, kotlin);
