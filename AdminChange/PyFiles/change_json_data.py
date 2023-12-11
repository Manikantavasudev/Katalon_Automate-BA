import json
import logging

logging.basicConfig(level=logging.INFO)


class ModifyAppPropertyData:

    def __init__(self):
        self.filename = "C:\GRL\AppData\AppProperty.json"
        self.modify_app_property_filedata(self.filename)

    def modify_app_property_filedata(self, filename):
        """
        1. Initialize the file path in __init__, which we need to change.
        2. Open file and read the file and change the value. "r+ means reading and writing the file"
        3. if the file is not found it will throw an FileNotFoundError.
        """
        try:
            with open(filename, "r+") as jsonFile:
                app_property_data = json.load(jsonFile)
                isadminmode_data = app_property_data["ISADMINMODE"]
                before_change = isadminmode_data["PropertyValue"]
                logging.info("Update of the PropertyValue is held up, before change "
                             "the value is {}".format(before_change))
                if isadminmode_data["PropertyValue"] is False:
                    app_property_data["ISADMINMODE"]["PropertyValue"] = True
                    after_change = isadminmode_data["PropertyValue"]
                    logging.info("PropertyValue is get change from {} to {} ".format(before_change, after_change))
                    jsonFile.seek(0)  # change the position of the File Handle to a given specific position.
                    json.dump(app_property_data, jsonFile)  # used when the Python objects have to be stored in a file.
                    jsonFile.truncate()  # resizes the file to the given number of bytes
                    jsonFile.close() # method closes an open file

        except FileNotFoundError as fnf_error:
            logging.error(fnf_error)
            logging.error("{} cannot be found in the mentioned path".format(self.filename))


def main():
    reader = ModifyAppPropertyData()


if __name__ == "__main__":
    main()
