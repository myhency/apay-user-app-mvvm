# apay-user-app-mvvm

## Activity 생성

- ViewModelProviderFactory 에 생성한 Activity 를 등록해 줘야 한다.
- ActivityBuilder 에 생성한 Activity 를 등록해 줘야 한다.

## Fragment 생성

1. 생성하려는 package 에서 new -> fragment(blank) 생성

2. xml 파일을 수정한다.
    - 시작은 무조건 `<layout>` 태그로 해야 함.

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:bind="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <import type="android.view.View" />

        <variable
            name="viewModel"
            type="com.autoever.apay_user_app.ui.user.register.RegisterViewModel" />

    </data>
    
    <!-- UI 작성 부분 -->
    ...
    ...

</layout>
```

3. SomeViewModel 클래스를 추가한다.

4. SomeFragmentProvider 클래스를 추가한다.

5. SomeFragment 클래스를 수정한다.

6. ViewModelProviderFactory 에 ViewModel 을 추가해 준다.

7. ActivityBuilder 에 module 로 등록해 준다.


## Api 추가

1. Request/Response model 을 작성한다.

2. RepoService 에 호출할 api 를 추가한다.

3. AppDataManager 에 override 한다.

4. SomeViewModel 에서 호출한다.