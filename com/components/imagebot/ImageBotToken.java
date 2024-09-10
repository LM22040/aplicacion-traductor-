package com.google.appinventor.components.runtime.imagebot;

import com.google.appinventor.components.runtime.Component;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class ImageBotToken {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_request_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_request_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_response_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_response_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_token_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_token_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_unsigned_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_unsigned_fieldAccessorTable;

    /* loaded from: classes.dex */
    public interface requestOrBuilder extends MessageOrBuilder {
        String getApikey();

        ByteString getApikeyBytes();

        ByteString getMask();

        request.OperationType getOperation();

        String getPrompt();

        ByteString getPromptBytes();

        String getSize();

        ByteString getSizeBytes();

        ByteString getSource();

        token getToken();

        tokenOrBuilder getTokenOrBuilder();

        long getVersion();

        boolean hasApikey();

        boolean hasMask();

        boolean hasOperation();

        boolean hasPrompt();

        boolean hasSize();

        boolean hasSource();

        boolean hasToken();

        boolean hasVersion();
    }

    /* loaded from: classes.dex */
    public interface responseOrBuilder extends MessageOrBuilder {
        ByteString getImage();

        long getStatus();

        long getVersion();

        boolean hasImage();

        boolean hasStatus();

        boolean hasVersion();
    }

    /* loaded from: classes.dex */
    public interface tokenOrBuilder extends MessageOrBuilder {
        long getGeneration();

        long getKeyid();

        ByteString getSignature();

        ByteString getUnsigned();

        long getVersion();

        boolean hasGeneration();

        boolean hasKeyid();

        boolean hasSignature();

        boolean hasUnsigned();

        boolean hasVersion();
    }

    /* loaded from: classes.dex */
    public interface unsignedOrBuilder extends MessageOrBuilder {
        long getGeneration();

        String getHuuid();

        ByteString getHuuidBytes();

        long getVersion();

        boolean hasGeneration();

        boolean hasHuuid();

        boolean hasVersion();
    }

    private ImageBotToken() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registerAllExtensions((ExtensionRegistryLite) registry);
    }

    /* loaded from: classes.dex */
    public static final class unsigned extends GeneratedMessageV3 implements unsignedOrBuilder {
        public static final int GENERATION_FIELD_NUMBER = 3;
        public static final int HUUID_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long generation_;
        private volatile Object huuid_;
        private byte memoizedIsInitialized;
        private long version_;
        private static final unsigned DEFAULT_INSTANCE = new unsigned();

        @Deprecated
        public static final Parser<unsigned> PARSER = new AbstractParser<unsigned>() { // from class: com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsigned.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public unsigned m807parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new unsigned(input, extensionRegistry);
            }
        };

        private unsigned(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private unsigned() {
            this.memoizedIsInitialized = (byte) -1;
            this.huuid_ = "";
            this.version_ = 0L;
            this.generation_ = 0L;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private unsigned(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 10:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.huuid_ = bs;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.version_ = input.readUInt64();
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.generation_ = input.readUInt64();
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ImageBotToken.internal_static_unsigned_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ImageBotToken.internal_static_unsigned_fieldAccessorTable.ensureFieldAccessorsInitialized(unsigned.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public boolean hasHuuid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public String getHuuid() {
            Object ref = this.huuid_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.huuid_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public ByteString getHuuidBytes() {
            Object ref = this.huuid_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.huuid_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public boolean hasGeneration() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
        public long getGeneration() {
            return this.generation_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(output, 1, this.huuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.version_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeUInt64(3, this.generation_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.huuid_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.version_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeUInt64Size(3, this.generation_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof unsigned)) {
                return super.equals(obj);
            }
            unsigned other = (unsigned) obj;
            boolean result = 1 != 0 && hasHuuid() == other.hasHuuid();
            if (hasHuuid()) {
                result = result && getHuuid().equals(other.getHuuid());
            }
            boolean result2 = result && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result2 = result2 && getVersion() == other.getVersion();
            }
            boolean result3 = result2 && hasGeneration() == other.hasGeneration();
            if (hasGeneration()) {
                result3 = result3 && getGeneration() == other.getGeneration();
            }
            return result3 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasHuuid()) {
                hash = (((hash * 37) + 1) * 53) + getHuuid().hashCode();
            }
            if (hasVersion()) {
                hash = (((hash * 37) + 2) * 53) + Internal.hashLong(getVersion());
            }
            if (hasGeneration()) {
                hash = (((hash * 37) + 3) * 53) + Internal.hashLong(getGeneration());
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static unsigned parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data);
        }

        public static unsigned parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data, extensionRegistry);
        }

        public static unsigned parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data);
        }

        public static unsigned parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (unsigned) PARSER.parseFrom(data, extensionRegistry);
        }

        public static unsigned parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static unsigned parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static unsigned parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static unsigned parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static unsigned parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static unsigned parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m804newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m806toBuilder();
        }

        public static Builder newBuilder(unsigned prototype) {
            return DEFAULT_INSTANCE.m806toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m806toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m803newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements unsignedOrBuilder {
            private int bitField0_;
            private long generation_;
            private Object huuid_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ImageBotToken.internal_static_unsigned_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ImageBotToken.internal_static_unsigned_fieldAccessorTable.ensureFieldAccessorsInitialized(unsigned.class, Builder.class);
            }

            private Builder() {
                this.huuid_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.huuid_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = unsigned.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m817clear() {
                super.clear();
                this.huuid_ = "";
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.version_ = 0L;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.generation_ = 0L;
                this.bitField0_ = i2 & (-5);
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ImageBotToken.internal_static_unsigned_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public unsigned m830getDefaultInstanceForType() {
                return unsigned.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public unsigned m811build() {
                unsigned result = m813buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public unsigned m813buildPartial() {
                unsigned result = new unsigned(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.huuid_ = this.huuid_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.generation_ = this.generation_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m828clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m841setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m819clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m822clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m843setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m809addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m835mergeFrom(Message other) {
                if (other instanceof unsigned) {
                    return mergeFrom((unsigned) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(unsigned other) {
                if (other == unsigned.getDefaultInstance()) {
                    return this;
                }
                if (other.hasHuuid()) {
                    this.bitField0_ |= 1;
                    this.huuid_ = other.huuid_;
                    onChanged();
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasGeneration()) {
                    setGeneration(other.getGeneration());
                }
                m839mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m836mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                unsigned parsedMessage = null;
                try {
                    try {
                        parsedMessage = (unsigned) unsigned.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public boolean hasHuuid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public String getHuuid() {
                Object ref = this.huuid_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.huuid_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public ByteString getHuuidBytes() {
                Object ref = this.huuid_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.huuid_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setHuuid(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.huuid_ = value;
                onChanged();
                return this;
            }

            public Builder clearHuuid() {
                this.bitField0_ &= -2;
                this.huuid_ = unsigned.getDefaultInstance().getHuuid();
                onChanged();
                return this;
            }

            public Builder setHuuidBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.huuid_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 2;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -3;
                this.version_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public boolean hasGeneration() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.unsignedOrBuilder
            public long getGeneration() {
                return this.generation_;
            }

            public Builder setGeneration(long value) {
                this.bitField0_ |= 4;
                this.generation_ = value;
                onChanged();
                return this;
            }

            public Builder clearGeneration() {
                this.bitField0_ &= -5;
                this.generation_ = 0L;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m845setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m839mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static unsigned getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<unsigned> parser() {
            return PARSER;
        }

        public Parser<unsigned> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public unsigned m801getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class token extends GeneratedMessageV3 implements tokenOrBuilder {
        public static final int GENERATION_FIELD_NUMBER = 3;
        public static final int KEYID_FIELD_NUMBER = 2;
        public static final int SIGNATURE_FIELD_NUMBER = 5;
        public static final int UNSIGNED_FIELD_NUMBER = 4;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long generation_;
        private long keyid_;
        private byte memoizedIsInitialized;
        private ByteString signature_;
        private ByteString unsigned_;
        private long version_;
        private static final token DEFAULT_INSTANCE = new token();

        @Deprecated
        public static final Parser<token> PARSER = new AbstractParser<token>() { // from class: com.google.appinventor.components.runtime.imagebot.ImageBotToken.token.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public token m756parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new token(input, extensionRegistry);
            }
        };

        private token(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private token() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 1L;
            this.keyid_ = 1L;
            this.generation_ = 0L;
            this.unsigned_ = ByteString.EMPTY;
            this.signature_ = ByteString.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private token(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.version_ = input.readUInt64();
                            case 16:
                                this.bitField0_ |= 2;
                                this.keyid_ = input.readUInt64();
                            case 24:
                                this.bitField0_ |= 4;
                                this.generation_ = input.readUInt64();
                            case 34:
                                this.bitField0_ |= 8;
                                this.unsigned_ = input.readBytes();
                            case 42:
                                this.bitField0_ |= 16;
                                this.signature_ = input.readBytes();
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ImageBotToken.internal_static_token_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ImageBotToken.internal_static_token_fieldAccessorTable.ensureFieldAccessorsInitialized(token.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public boolean hasKeyid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public long getKeyid() {
            return this.keyid_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public boolean hasGeneration() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public long getGeneration() {
            return this.generation_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public boolean hasUnsigned() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public ByteString getUnsigned() {
            return this.unsigned_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt64(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.keyid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeUInt64(3, this.generation_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, this.unsigned_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, this.signature_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt64Size(1, this.version_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.keyid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeUInt64Size(3, this.generation_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, this.unsigned_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, this.signature_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof token)) {
                return super.equals(obj);
            }
            token other = (token) obj;
            boolean result = 1 != 0 && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result = result && getVersion() == other.getVersion();
            }
            boolean result2 = result && hasKeyid() == other.hasKeyid();
            if (hasKeyid()) {
                result2 = result2 && getKeyid() == other.getKeyid();
            }
            boolean result3 = result2 && hasGeneration() == other.hasGeneration();
            if (hasGeneration()) {
                result3 = result3 && getGeneration() == other.getGeneration();
            }
            boolean result4 = result3 && hasUnsigned() == other.hasUnsigned();
            if (hasUnsigned()) {
                result4 = result4 && getUnsigned().equals(other.getUnsigned());
            }
            boolean result5 = result4 && hasSignature() == other.hasSignature();
            if (hasSignature()) {
                result5 = result5 && getSignature().equals(other.getSignature());
            }
            return result5 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasVersion()) {
                hash = (((hash * 37) + 1) * 53) + Internal.hashLong(getVersion());
            }
            if (hasKeyid()) {
                hash = (((hash * 37) + 2) * 53) + Internal.hashLong(getKeyid());
            }
            if (hasGeneration()) {
                hash = (((hash * 37) + 3) * 53) + Internal.hashLong(getGeneration());
            }
            if (hasUnsigned()) {
                hash = (((hash * 37) + 4) * 53) + getUnsigned().hashCode();
            }
            if (hasSignature()) {
                hash = (((hash * 37) + 5) * 53) + getSignature().hashCode();
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static token parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data);
        }

        public static token parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data, extensionRegistry);
        }

        public static token parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data);
        }

        public static token parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (token) PARSER.parseFrom(data, extensionRegistry);
        }

        public static token parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static token parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static token parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static token parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static token parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static token parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m753newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m755toBuilder();
        }

        public static Builder newBuilder(token prototype) {
            return DEFAULT_INSTANCE.m755toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m755toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m752newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements tokenOrBuilder {
            private int bitField0_;
            private long generation_;
            private long keyid_;
            private ByteString signature_;
            private ByteString unsigned_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ImageBotToken.internal_static_token_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ImageBotToken.internal_static_token_fieldAccessorTable.ensureFieldAccessorsInitialized(token.class, Builder.class);
            }

            private Builder() {
                this.version_ = 1L;
                this.keyid_ = 1L;
                this.unsigned_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.version_ = 1L;
                this.keyid_ = 1L;
                this.unsigned_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = token.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m766clear() {
                super.clear();
                this.version_ = 1L;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.keyid_ = 1L;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.generation_ = 0L;
                this.bitField0_ = i2 & (-5);
                this.unsigned_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ImageBotToken.internal_static_token_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public token m779getDefaultInstanceForType() {
                return token.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public token m760build() {
                token result = m762buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public token m762buildPartial() {
                token result = new token(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.keyid_ = this.keyid_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.generation_ = this.generation_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.unsigned_ = this.unsigned_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.signature_ = this.signature_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m777clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m790setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m768clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m771clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m792setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m758addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m784mergeFrom(Message other) {
                if (other instanceof token) {
                    return mergeFrom((token) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(token other) {
                if (other == token.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasKeyid()) {
                    setKeyid(other.getKeyid());
                }
                if (other.hasGeneration()) {
                    setGeneration(other.getGeneration());
                }
                if (other.hasUnsigned()) {
                    setUnsigned(other.getUnsigned());
                }
                if (other.hasSignature()) {
                    setSignature(other.getSignature());
                }
                m788mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m785mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                token parsedMessage = null;
                try {
                    try {
                        parsedMessage = (token) token.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public boolean hasKeyid() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public long getKeyid() {
                return this.keyid_;
            }

            public Builder setKeyid(long value) {
                this.bitField0_ |= 2;
                this.keyid_ = value;
                onChanged();
                return this;
            }

            public Builder clearKeyid() {
                this.bitField0_ &= -3;
                this.keyid_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public boolean hasGeneration() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public long getGeneration() {
                return this.generation_;
            }

            public Builder setGeneration(long value) {
                this.bitField0_ |= 4;
                this.generation_ = value;
                onChanged();
                return this;
            }

            public Builder clearGeneration() {
                this.bitField0_ &= -5;
                this.generation_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public boolean hasUnsigned() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public ByteString getUnsigned() {
                return this.unsigned_;
            }

            public Builder setUnsigned(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.unsigned_ = value;
                onChanged();
                return this;
            }

            public Builder clearUnsigned() {
                this.bitField0_ &= -9;
                this.unsigned_ = token.getDefaultInstance().getUnsigned();
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public boolean hasSignature() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.tokenOrBuilder
            public ByteString getSignature() {
                return this.signature_;
            }

            public Builder setSignature(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.signature_ = value;
                onChanged();
                return this;
            }

            public Builder clearSignature() {
                this.bitField0_ &= -17;
                this.signature_ = token.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m794setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m788mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static token getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<token> parser() {
            return PARSER;
        }

        public Parser<token> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public token m750getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class request extends GeneratedMessageV3 implements requestOrBuilder {
        public static final int APIKEY_FIELD_NUMBER = 7;
        public static final int MASK_FIELD_NUMBER = 6;
        public static final int OPERATION_FIELD_NUMBER = 2;
        public static final int PROMPT_FIELD_NUMBER = 4;
        public static final int SIZE_FIELD_NUMBER = 8;
        public static final int SOURCE_FIELD_NUMBER = 5;
        public static final int TOKEN_FIELD_NUMBER = 3;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object apikey_;
        private int bitField0_;
        private ByteString mask_;
        private byte memoizedIsInitialized;
        private int operation_;
        private volatile Object prompt_;
        private volatile Object size_;
        private ByteString source_;
        private token token_;
        private long version_;
        private static final request DEFAULT_INSTANCE = new request();

        @Deprecated
        public static final Parser<request> PARSER = new AbstractParser<request>() { // from class: com.google.appinventor.components.runtime.imagebot.ImageBotToken.request.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public request m653parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new request(input, extensionRegistry);
            }
        };

        private request(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private request() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 1L;
            this.operation_ = 0;
            this.prompt_ = "";
            this.source_ = ByteString.EMPTY;
            this.mask_ = ByteString.EMPTY;
            this.apikey_ = "";
            this.size_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private request(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.version_ = input.readUInt64();
                                case 16:
                                    int rawValue = input.readEnum();
                                    OperationType value = OperationType.valueOf(rawValue);
                                    if (value != null) {
                                        this.bitField0_ = 2 | this.bitField0_;
                                        this.operation_ = rawValue;
                                    } else {
                                        unknownFields.mergeVarintField(2, rawValue);
                                    }
                                case 26:
                                    token.Builder subBuilder = (this.bitField0_ & 4) == 4 ? this.token_.m755toBuilder() : null;
                                    token tokenVar = (token) input.readMessage(token.PARSER, extensionRegistry);
                                    this.token_ = tokenVar;
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(tokenVar);
                                        this.token_ = subBuilder.m762buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                case 34:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 8;
                                    this.prompt_ = bs;
                                case 42:
                                    this.bitField0_ |= 16;
                                    this.source_ = input.readBytes();
                                case 50:
                                    this.bitField0_ |= 32;
                                    this.mask_ = input.readBytes();
                                case 58:
                                    ByteString bs2 = input.readBytes();
                                    this.bitField0_ |= 64;
                                    this.apikey_ = bs2;
                                case 66:
                                    ByteString bs3 = input.readBytes();
                                    this.bitField0_ |= 128;
                                    this.size_ = bs3;
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ImageBotToken.internal_static_request_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ImageBotToken.internal_static_request_fieldAccessorTable.ensureFieldAccessorsInitialized(request.class, Builder.class);
        }

        /* loaded from: classes.dex */
        public enum OperationType implements ProtocolMessageEnum {
            CREATE(0),
            EDIT(1);

            public static final int CREATE_VALUE = 0;
            public static final int EDIT_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<OperationType> internalValueMap = new Internal.EnumLiteMap<OperationType>() { // from class: com.google.appinventor.components.runtime.imagebot.ImageBotToken.request.OperationType.1
                /* renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public OperationType m692findValueByNumber(int number) {
                    return OperationType.forNumber(number);
                }
            };
            private static final OperationType[] VALUES = values();

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OperationType valueOf(int value) {
                return forNumber(value);
            }

            public static OperationType forNumber(int value) {
                switch (value) {
                    case 0:
                        return CREATE;
                    case 1:
                        return EDIT;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<OperationType> internalGetValueMap() {
                return internalValueMap;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return (Descriptors.EnumDescriptor) request.getDescriptor().getEnumTypes().get(0);
            }

            public static OperationType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            OperationType(int value) {
                this.value = value;
            }
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasOperation() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public OperationType getOperation() {
            OperationType result = OperationType.valueOf(this.operation_);
            return result == null ? OperationType.CREATE : result;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasToken() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public token getToken() {
            token tokenVar = this.token_;
            return tokenVar == null ? token.getDefaultInstance() : tokenVar;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public tokenOrBuilder getTokenOrBuilder() {
            token tokenVar = this.token_;
            return tokenVar == null ? token.getDefaultInstance() : tokenVar;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasPrompt() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public String getPrompt() {
            Object ref = this.prompt_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.prompt_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public ByteString getPromptBytes() {
            Object ref = this.prompt_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.prompt_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasSource() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public ByteString getSource() {
            return this.source_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasMask() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public ByteString getMask() {
            return this.mask_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasApikey() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public String getApikey() {
            Object ref = this.apikey_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.apikey_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public ByteString getApikeyBytes() {
            Object ref = this.apikey_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.apikey_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public boolean hasSize() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public String getSize() {
            Object ref = this.size_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.size_ = s;
            }
            return s;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
        public ByteString getSizeBytes() {
            Object ref = this.size_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.size_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasOperation()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt64(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.operation_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getToken());
            }
            if ((this.bitField0_ & 8) == 8) {
                GeneratedMessageV3.writeString(output, 4, this.prompt_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, this.source_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBytes(6, this.mask_);
            }
            if ((this.bitField0_ & 64) == 64) {
                GeneratedMessageV3.writeString(output, 7, this.apikey_);
            }
            if ((this.bitField0_ & 128) == 128) {
                GeneratedMessageV3.writeString(output, 8, this.size_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt64Size(1, this.version_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.operation_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getToken());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += GeneratedMessageV3.computeStringSize(4, this.prompt_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, this.source_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBytesSize(6, this.mask_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += GeneratedMessageV3.computeStringSize(7, this.apikey_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += GeneratedMessageV3.computeStringSize(8, this.size_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof request)) {
                return super.equals(obj);
            }
            request other = (request) obj;
            boolean result = 1 != 0 && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result = result && getVersion() == other.getVersion();
            }
            boolean result2 = result && hasOperation() == other.hasOperation();
            if (hasOperation()) {
                result2 = result2 && this.operation_ == other.operation_;
            }
            boolean result3 = result2 && hasToken() == other.hasToken();
            if (hasToken()) {
                result3 = result3 && getToken().equals(other.getToken());
            }
            boolean result4 = result3 && hasPrompt() == other.hasPrompt();
            if (hasPrompt()) {
                result4 = result4 && getPrompt().equals(other.getPrompt());
            }
            boolean result5 = result4 && hasSource() == other.hasSource();
            if (hasSource()) {
                result5 = result5 && getSource().equals(other.getSource());
            }
            boolean result6 = result5 && hasMask() == other.hasMask();
            if (hasMask()) {
                result6 = result6 && getMask().equals(other.getMask());
            }
            boolean result7 = result6 && hasApikey() == other.hasApikey();
            if (hasApikey()) {
                result7 = result7 && getApikey().equals(other.getApikey());
            }
            boolean result8 = result7 && hasSize() == other.hasSize();
            if (hasSize()) {
                result8 = result8 && getSize().equals(other.getSize());
            }
            return result8 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasVersion()) {
                hash = (((hash * 37) + 1) * 53) + Internal.hashLong(getVersion());
            }
            if (hasOperation()) {
                hash = (((hash * 37) + 2) * 53) + this.operation_;
            }
            if (hasToken()) {
                hash = (((hash * 37) + 3) * 53) + getToken().hashCode();
            }
            if (hasPrompt()) {
                hash = (((hash * 37) + 4) * 53) + getPrompt().hashCode();
            }
            if (hasSource()) {
                hash = (((hash * 37) + 5) * 53) + getSource().hashCode();
            }
            if (hasMask()) {
                hash = (((hash * 37) + 6) * 53) + getMask().hashCode();
            }
            if (hasApikey()) {
                hash = (((hash * 37) + 7) * 53) + getApikey().hashCode();
            }
            if (hasSize()) {
                hash = (((hash * 37) + 8) * 53) + getSize().hashCode();
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static request parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data);
        }

        public static request parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data, extensionRegistry);
        }

        public static request parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data);
        }

        public static request parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (request) PARSER.parseFrom(data, extensionRegistry);
        }

        public static request parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static request parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static request parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static request parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static request parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static request parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m650newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m652toBuilder();
        }

        public static Builder newBuilder(request prototype) {
            return DEFAULT_INSTANCE.m652toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m652toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m649newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements requestOrBuilder {
            private Object apikey_;
            private int bitField0_;
            private ByteString mask_;
            private int operation_;
            private Object prompt_;
            private Object size_;
            private ByteString source_;
            private SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> tokenBuilder_;
            private token token_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ImageBotToken.internal_static_request_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ImageBotToken.internal_static_request_fieldAccessorTable.ensureFieldAccessorsInitialized(request.class, Builder.class);
            }

            private Builder() {
                this.version_ = 1L;
                this.operation_ = 0;
                this.token_ = null;
                this.prompt_ = "";
                this.source_ = ByteString.EMPTY;
                this.mask_ = ByteString.EMPTY;
                this.apikey_ = "";
                this.size_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.version_ = 1L;
                this.operation_ = 0;
                this.token_ = null;
                this.prompt_ = "";
                this.source_ = ByteString.EMPTY;
                this.mask_ = ByteString.EMPTY;
                this.apikey_ = "";
                this.size_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (request.alwaysUseFieldBuilders) {
                    getTokenFieldBuilder();
                }
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m663clear() {
                super.clear();
                this.version_ = 1L;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.operation_ = 0;
                this.bitField0_ = i & (-3);
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.token_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                int i2 = this.bitField0_ & (-5);
                this.bitField0_ = i2;
                this.prompt_ = "";
                this.bitField0_ = i2 & (-9);
                this.source_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                this.mask_ = ByteString.EMPTY;
                int i3 = this.bitField0_ & (-33);
                this.bitField0_ = i3;
                this.apikey_ = "";
                int i4 = i3 & (-65);
                this.bitField0_ = i4;
                this.size_ = "";
                this.bitField0_ = i4 & (-129);
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ImageBotToken.internal_static_request_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public request m676getDefaultInstanceForType() {
                return request.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public request m657build() {
                request result = m659buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public request m659buildPartial() {
                request result = new request(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.operation_ = this.operation_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    result.token_ = this.token_;
                } else {
                    result.token_ = singleFieldBuilderV3.build();
                }
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.prompt_ = this.prompt_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.source_ = this.source_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.mask_ = this.mask_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.apikey_ = this.apikey_;
                if ((from_bitField0_ & 128) == 128) {
                    to_bitField0_ |= 128;
                }
                result.size_ = this.size_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m674clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m687setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m665clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m668clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m689setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m655addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m681mergeFrom(Message other) {
                if (other instanceof request) {
                    return mergeFrom((request) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(request other) {
                if (other == request.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasOperation()) {
                    setOperation(other.getOperation());
                }
                if (other.hasToken()) {
                    mergeToken(other.getToken());
                }
                if (other.hasPrompt()) {
                    this.bitField0_ |= 8;
                    this.prompt_ = other.prompt_;
                    onChanged();
                }
                if (other.hasSource()) {
                    setSource(other.getSource());
                }
                if (other.hasMask()) {
                    setMask(other.getMask());
                }
                if (other.hasApikey()) {
                    this.bitField0_ |= 64;
                    this.apikey_ = other.apikey_;
                    onChanged();
                }
                if (other.hasSize()) {
                    this.bitField0_ |= 128;
                    this.size_ = other.size_;
                    onChanged();
                }
                m685mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!hasOperation()) {
                    return false;
                }
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m682mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                request parsedMessage = null;
                try {
                    try {
                        parsedMessage = (request) request.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasOperation() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public OperationType getOperation() {
                OperationType result = OperationType.valueOf(this.operation_);
                return result == null ? OperationType.CREATE : result;
            }

            public Builder setOperation(OperationType value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.operation_ = value.getNumber();
                onChanged();
                return this;
            }

            public Builder clearOperation() {
                this.bitField0_ &= -3;
                this.operation_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasToken() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public token getToken() {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    token tokenVar = this.token_;
                    return tokenVar == null ? token.getDefaultInstance() : tokenVar;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setToken(token value) {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.token_ = value;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setToken(token.Builder builderForValue) {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.token_ = builderForValue.m760build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builderForValue.m760build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeToken(token value) {
                token tokenVar;
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4 && (tokenVar = this.token_) != null && tokenVar != token.getDefaultInstance()) {
                        this.token_ = token.newBuilder(this.token_).mergeFrom(value).m762buildPartial();
                    } else {
                        this.token_ = value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearToken() {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.token_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public token.Builder getTokenBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return getTokenFieldBuilder().getBuilder();
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public tokenOrBuilder getTokenOrBuilder() {
                SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> singleFieldBuilderV3 = this.tokenBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (tokenOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                token tokenVar = this.token_;
                if (tokenVar != null) {
                    return tokenVar;
                }
                return token.getDefaultInstance();
            }

            private SingleFieldBuilderV3<token, token.Builder, tokenOrBuilder> getTokenFieldBuilder() {
                if (this.tokenBuilder_ == null) {
                    this.tokenBuilder_ = new SingleFieldBuilderV3<>(getToken(), getParentForChildren(), isClean());
                    this.token_ = null;
                }
                return this.tokenBuilder_;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasPrompt() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public String getPrompt() {
                Object ref = this.prompt_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.prompt_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public ByteString getPromptBytes() {
                Object ref = this.prompt_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.prompt_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setPrompt(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.prompt_ = value;
                onChanged();
                return this;
            }

            public Builder clearPrompt() {
                this.bitField0_ &= -9;
                this.prompt_ = request.getDefaultInstance().getPrompt();
                onChanged();
                return this;
            }

            public Builder setPromptBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.prompt_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasSource() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public ByteString getSource() {
                return this.source_;
            }

            public Builder setSource(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.source_ = value;
                onChanged();
                return this;
            }

            public Builder clearSource() {
                this.bitField0_ &= -17;
                this.source_ = request.getDefaultInstance().getSource();
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasMask() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public ByteString getMask() {
                return this.mask_;
            }

            public Builder setMask(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.mask_ = value;
                onChanged();
                return this;
            }

            public Builder clearMask() {
                this.bitField0_ &= -33;
                this.mask_ = request.getDefaultInstance().getMask();
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasApikey() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public String getApikey() {
                Object ref = this.apikey_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.apikey_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public ByteString getApikeyBytes() {
                Object ref = this.apikey_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.apikey_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setApikey(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.apikey_ = value;
                onChanged();
                return this;
            }

            public Builder clearApikey() {
                this.bitField0_ &= -65;
                this.apikey_ = request.getDefaultInstance().getApikey();
                onChanged();
                return this;
            }

            public Builder setApikeyBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.apikey_ = value;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public boolean hasSize() {
                return (this.bitField0_ & 128) == 128;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public String getSize() {
                Object ref = this.size_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.size_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.requestOrBuilder
            public ByteString getSizeBytes() {
                Object ref = this.size_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.size_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSize(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.size_ = value;
                onChanged();
                return this;
            }

            public Builder clearSize() {
                this.bitField0_ &= -129;
                this.size_ = request.getDefaultInstance().getSize();
                onChanged();
                return this;
            }

            public Builder setSizeBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.size_ = value;
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m691setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m685mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<request> parser() {
            return PARSER;
        }

        public Parser<request> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public request m647getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes.dex */
    public static final class response extends GeneratedMessageV3 implements responseOrBuilder {
        public static final int IMAGE_FIELD_NUMBER = 3;
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ByteString image_;
        private byte memoizedIsInitialized;
        private long status_;
        private long version_;
        private static final response DEFAULT_INSTANCE = new response();

        @Deprecated
        public static final Parser<response> PARSER = new AbstractParser<response>() { // from class: com.google.appinventor.components.runtime.imagebot.ImageBotToken.response.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public response m704parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new response(input, extensionRegistry);
            }
        };

        private response(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private response() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = 1L;
            this.status_ = 0L;
            this.image_ = ByteString.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000f. Please report as an issue. */
        private response(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.version_ = input.readUInt64();
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.status_ = input.readUInt64();
                                case 26:
                                    this.bitField0_ |= 4;
                                    this.image_ = input.readBytes();
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ImageBotToken.internal_static_response_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ImageBotToken.internal_static_response_fieldAccessorTable.ensureFieldAccessorsInitialized(response.class, Builder.class);
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
        public long getVersion() {
            return this.version_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
        public long getStatus() {
            return this.status_;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
        public boolean hasImage() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
        public ByteString getImage() {
            return this.image_;
        }

        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt64(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.status_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.image_);
            }
            this.unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = this.memoizedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt64Size(1, this.version_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.status_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, this.image_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = size3;
            return size3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof response)) {
                return super.equals(obj);
            }
            response other = (response) obj;
            boolean result = 1 != 0 && hasVersion() == other.hasVersion();
            if (hasVersion()) {
                result = result && getVersion() == other.getVersion();
            }
            boolean result2 = result && hasStatus() == other.hasStatus();
            if (hasStatus()) {
                result2 = result2 && getStatus() == other.getStatus();
            }
            boolean result3 = result2 && hasImage() == other.hasImage();
            if (hasImage()) {
                result3 = result3 && getImage().equals(other.getImage());
            }
            return result3 && this.unknownFields.equals(other.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hash = (41 * 19) + getDescriptorForType().hashCode();
            if (hasVersion()) {
                hash = (((hash * 37) + 1) * 53) + Internal.hashLong(getVersion());
            }
            if (hasStatus()) {
                hash = (((hash * 37) + 2) * 53) + Internal.hashLong(getStatus());
            }
            if (hasImage()) {
                hash = (((hash * 37) + 3) * 53) + getImage().hashCode();
            }
            int hash2 = (hash * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hash2;
            return hash2;
        }

        public static response parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data);
        }

        public static response parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data, extensionRegistry);
        }

        public static response parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data);
        }

        public static response parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (response) PARSER.parseFrom(data, extensionRegistry);
        }

        public static response parseFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static response parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static response parseDelimitedFrom(InputStream input) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static response parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static response parseFrom(CodedInputStream input) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static response parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m701newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m703toBuilder();
        }

        public static Builder newBuilder(response prototype) {
            return DEFAULT_INSTANCE.m703toBuilder().mergeFrom(prototype);
        }

        /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m703toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder m700newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements responseOrBuilder {
            private int bitField0_;
            private ByteString image_;
            private long status_;
            private long version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ImageBotToken.internal_static_response_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ImageBotToken.internal_static_response_fieldAccessorTable.ensureFieldAccessorsInitialized(response.class, Builder.class);
            }

            private Builder() {
                this.version_ = 1L;
                this.image_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                this.version_ = 1L;
                this.image_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = response.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m714clear() {
                super.clear();
                this.version_ = 1L;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.status_ = 0L;
                this.bitField0_ = i & (-3);
                this.image_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ImageBotToken.internal_static_response_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public response m727getDefaultInstanceForType() {
                return response.getDefaultInstance();
            }

            /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public response m708build() {
                response result = m710buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public response m710buildPartial() {
                response result = new response(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.status_ = this.status_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.image_ = this.image_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m725clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m738setField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.setField(field, value);
            }

            /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m716clearField(Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m719clearOneof(Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m740setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m706addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m732mergeFrom(Message other) {
                if (other instanceof response) {
                    return mergeFrom((response) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(response other) {
                if (other == response.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasStatus()) {
                    setStatus(other.getStatus());
                }
                if (other.hasImage()) {
                    setImage(other.getImage());
                }
                m736mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m733mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                response parsedMessage = null;
                try {
                    try {
                        parsedMessage = (response) response.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        e.getUnfinishedMessage();
                        throw e.unwrapIOException();
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
            public long getVersion() {
                return this.version_;
            }

            public Builder setVersion(long value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 1L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
            public long getStatus() {
                return this.status_;
            }

            public Builder setStatus(long value) {
                this.bitField0_ |= 2;
                this.status_ = value;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.bitField0_ &= -3;
                this.status_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
            public boolean hasImage() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.appinventor.components.runtime.imagebot.ImageBotToken.responseOrBuilder
            public ByteString getImage() {
                return this.image_;
            }

            public Builder setImage(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.image_ = value;
                onChanged();
                return this;
            }

            public Builder clearImage() {
                this.bitField0_ &= -5;
                this.image_ = response.getDefaultInstance().getImage();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m742setUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.setUnknownFields(unknownFields);
            }

            /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m736mergeUnknownFields(UnknownFieldSet unknownFields) {
                return (Builder) super.mergeUnknownFields(unknownFields);
            }
        }

        public static response getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<response> parser() {
            return PARSER;
        }

        public Parser<response> getParserForType() {
            return PARSER;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public response m698getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {"\n\u000bimage.proto\"D\n\bunsigned\u0012\r\n\u0005huuid\u0018\u0001 \u0001(\t\u0012\u0012\n\u0007version\u0018\u0002 \u0001(\u0004:\u00010\u0012\u0015\n\ngeneration\u0018\u0003 \u0001(\u0004:\u00010\"i\n\u0005token\u0012\u0012\n\u0007version\u0018\u0001 \u0001(\u0004:\u00011\u0012\u0010\n\u0005keyid\u0018\u0002 \u0001(\u0004:\u00011\u0012\u0015\n\ngeneration\u0018\u0003 \u0001(\u0004:\u00010\u0012\u0010\n\bunsigned\u0018\u0004 \u0001(\f\u0012\u0011\n\tsignature\u0018\u0005 \u0001(\f\"Ò\u0001\n\u0007request\u0012\u0012\n\u0007version\u0018\u0001 \u0001(\u0004:\u00011\u0012)\n\toperation\u0018\u0002 \u0002(\u000e2\u0016.request.OperationType\u0012\u0015\n\u0005token\u0018\u0003 \u0001(\u000b2\u0006.token\u0012\u000e\n\u0006prompt\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006source\u0018\u0005 \u0001(\f\u0012\f\n\u0004mask\u0018\u0006 \u0001(\f\u0012\u000e\n\u0006apikey\u0018\u0007 \u0001(\t\u0012\f\n\u0004size\u0018\b \u0001(\t\"%\n\rOperationType\u0012\n\n\u0006CREATE\u0010\u0000\u0012\b\n\u0004EDI", "T\u0010\u0001\"@\n\bresponse\u0012\u0012\n\u0007version\u0018\u0001 \u0001(\u0004:\u00011\u0012\u0011\n\u0006status\u0018\u0002 \u0001(\u0004:\u00010\u0012\r\n\u0005image\u0018\u0003 \u0001(\fBC\n2com.google.appinventor.components.runtime.imagebotB\rImageBotToken"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.appinventor.components.runtime.imagebot.ImageBotToken.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                ImageBotToken.descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_unsigned_descriptor = descriptor2;
        internal_static_unsigned_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Huuid", "Version", "Generation"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_token_descriptor = descriptor3;
        internal_static_token_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Version", "Keyid", "Generation", "Unsigned", "Signature"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_request_descriptor = descriptor4;
        internal_static_request_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Version", "Operation", "Token", "Prompt", "Source", "Mask", "Apikey", "Size"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_response_descriptor = descriptor5;
        internal_static_response_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Version", "Status", Component.LISTVIEW_KEY_IMAGE});
    }
}
