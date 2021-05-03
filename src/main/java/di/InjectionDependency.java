package di;

import modules.auth.domain.repository.IAuthRepository;
import modules.auth.domain.usecases.RegisterUserUsecase;

import java.util.HashMap;
import java.util.Map;

public class InjectionDependency {
    private Map<Object, Object> _dependencies = new HashMap<>();

    private static InjectionDependency instance;

    private InjectionDependency() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static InjectionDependency getInstance() {
        if (instance == null) {
            instance = new InjectionDependency();
        }
        return instance;
    }

    public void register(Object type, Object bind){
        _dependencies.put(type, bind);
    }

    public void cleanModule() {
        _dependencies.clear();
    }

    public Object get(Object type){
        return _dependencies.get(type);
    }

}
