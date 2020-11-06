package CustomDataRepository;

import CusstomDataRepositoryParser.CustomParser;
import model.Entity;
import model.SearchParameters;
import repository.Search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomDataRepository implements repository.DataRepository {

    CustomParser parser = new CustomParser();
    Search customSearch = new Search();

    @Override
    public void save(String s, Object o) {

            List<Entity> objects = parser.reader(new File(s));
            if(!objects.isEmpty()) {
                objects.add((Entity) o);
                parser.writer(new File(s), objects);
            }
            else {
                List<Entity> objects1 = new ArrayList<>();
                objects1.add((Entity) o);
                parser.writer(new File(s), objects1);
            }
    }

    @Override
    public ArrayList<Entity> find(SearchParameters searchParameters) {
        List<Entity> entities = parser.reader(new File("D:\\custom.txt"));
        return customSearch.find(entities,searchParameters);
    }


    @Override
    public <T> List<T> findAll(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public void delete(String s, int id) {
        List<Entity> objects = parser.reader(new File(s));
        for(Entity entity : objects){
            if(entity.getId()==id) {
                objects.remove(entity);
                break;
            }
        }
        parser.writer(new File(s), objects);
    }

    @Override
    public void update(String s, int id, HashMap<Object, Object> updatedMap) {

        List<Entity> objects = parser.reader(new File(s));
        for(Entity entity : objects){
            if(entity.getId()==id) {
                updatedMap.forEach((key,value)-> entity.getAttributes().put(key,value));
                break;
            }
        }
        parser.writer(new File(s), objects);
    }
}
