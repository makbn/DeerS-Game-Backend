package database;

import com.onyx.exception.EntityException;
import com.onyx.exception.InitializationException;
import com.onyx.persistence.IManagedEntity;
import com.onyx.persistence.factory.impl.CacheManagerFactory;
import com.onyx.persistence.manager.PersistenceManager;
import util.DeerLogger;

import java.io.File;

/**
 * Created by white on 2016-11-07.
 */
public class DbManager {

    private static DbManager mDbManager;
    private static final String DB_USERNAME="admin";
    private static final String DB_PASSWORD="admin";
    private static final String DB_NAME="embedded-db";
    private static final String DB_PATH= ".."+ File.separator+".onyxdb"+File.separator+DB_NAME+".oxd";
    private static CacheManagerFactory cacheManagerFactory;
    private static PersistenceManager manager;
    private DbManager() throws InitializationException {
        cacheManagerFactory=new CacheManagerFactory();
        cacheManagerFactory.setCredentials(DB_USERNAME,DB_PASSWORD);
        cacheManagerFactory.setDatabaseLocation(DB_PATH);
        cacheManagerFactory.initialize();
        manager = cacheManagerFactory.getPersistenceManager();
    }

    /**
     * get instance of db manager to access to database
     * @return
     */
    public static DbManager getInstance() throws InitializationException {
        if(mDbManager==null){
            mDbManager=new DbManager();
        }
        return mDbManager;
    }

    /**
     * A Cache Manager Factory is used to configure your In Memory Database.
     * Onyx Cache compresses and indexes your data.
     * It allows you to take advantage of accessing and storing entities using off-heap resources
     * while also taking advantage of all the Onyx Database ORM features.
     * @param obj should extends {@link com.onyx.persistence.ManagedEntity}
     *            or implements {@link IManagedEntity}
     */
    public void cacheToMemory(IManagedEntity obj) throws EntityException {
        manager.saveEntity(obj);
        DeerLogger.getInstance().onInfo(this.getClass(),DeerLogger.WRITE_TO_DB +obj.toString());
        cacheManagerFactory.close();
    }

    private void close(){
        cacheManagerFactory.close();
    }
}
