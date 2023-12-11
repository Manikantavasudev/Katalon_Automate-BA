from collections import OrderedDict


class TestNameResultData:
    def __init__(self):
        try:
            self.test_name = ""
            self.test_id = ""
            self.run_data = OrderedDict()

        except Exception as e:
            print("TestNameResultData _ __init__ :-" + str(e))
